package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import admin.service.AdminService;
import print.dto.PageDTO;
import user.dto.UserDTO;

//http://localhost:8090/myapp/admin

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;  // 서비스
	private PageDTO pdto;  // pageDTO 

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setPdto(PageDTO pdto) {
		this.pdto = pdto;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String main(HttpSession session) {
		if (session.getAttribute("authInfo") != null) {  //사용자 페이지에서 로그인하거나 회원가입시 생기는 authinfo 가 있을시
			System.out.println("authInfo존재"); // 있다면 홈으로 리다이렉트
			return "redirect:/"; 
		}
		return "admin";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		if (session.getAttribute("authInfo") != null) { //사용자 페이지에서 로그인하거나 회원가입시 생기는 authinfo 가 있을시
			System.out.println("authInfo존재"); // 홈으로 리다이렉트
			return "redirect:/";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/admin/adminSignup", method = RequestMethod.GET)
	public String signup(HttpSession session) {
		if (session.getAttribute("authInfo") != null) { //사용자 페이지에서 로그인하거나 회원가입시 생기는 authinfo 가 있을시
			System.out.println("authInfo존재"); //홈으로 
			return "redirect:/";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/admin/adminSignup", method = RequestMethod.POST)
	public String processAdminSignup(AdminDTO dto, HttpSession session) {
		// 회원가입 처리 로직
		adminAuthInfo adminauthInfo = adminService.addAdminProcess(dto); // 회원가입시 추가 
		session.setAttribute("adminauthInfo", adminauthInfo); //세션에 adminauthinfo 추가
		session.setAttribute("adminName", dto.getadmin_name()); //세션에 admindto의 이름 추가 회원가입시 입력한 이름
		return "redirect:/admin/user"; // 회원가입 후 이동할 페이지
	}

	@RequestMapping(value = "admin/login", method = RequestMethod.POST)
	public String processLogin(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			HttpSession session, HttpServletResponse resp) {
		//requestparam으로 가져온 이름이 loginId, loingPw 인 값들을 
		adminAuthInfo login = new adminAuthInfo(loginId, loginPw); // adminauthinfo를 생성한다.
		AdminDTO dto = adminService.loginProcess(login); // 로그인 기능 수행 데이터베이스에서 검색해서 결과 있는지 확인
		if (dto != null) { //검색시 일치하는 dto가 있다면 --> 아이디 비밀번호가 일치하는 데이터가 있다면 
			session.setAttribute("adminauthInfo", login);  //adminauthinfo 추가
			session.setAttribute("adminName", dto.getadmin_name()); // 이름 추가
			return "redirect:/admin/user"; //유저관리창으로 리다이렉트
		} else { // 없다면
			resp.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out;
			try {
				out = resp.getWriter();
				out.print("<script>alert('아이디 비밀번호 불일치'); history.go(-1);</script>");  //alert출력
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "redirect:/admin/user"; // 로그인 후 이동할 페이지
	}
	// 로그아웃

	@RequestMapping(value = "/admin/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate(); //세션값 지움
		return "redirect:/admin"; // 로그아웃후 admin 페이지로
	}

	// 아이디 중복체크
	@RequestMapping(value = "admin/idcheck", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(@RequestParam("id") String id) {
		System.out.println("컨트롤러 실행");
		System.out.println(id);
		int cnt = adminService.idcheck(id);
		String a = String.valueOf(cnt);
		return a;

	}

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String user(Model model, PageDTO pv) {
		List<UserDTO> dtos = null;
		int totalCount = adminService.countUserAllService();
		// 페이지네이션을 위한 결과 개수 가져오기
		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			model.addAttribute("pv", this.pdto); //전체 수에 따른 pv 추가
			model.addAttribute("number",pdto.getNumber());

		}
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow()); 
		dtos = adminService.printUserService(this.pdto); //전체 조회
		System.out.println(dtos.size());
		model.addAttribute("dtos", dtos); // 전체 리스트 추가
		model.addAttribute("count",totalCount);
		
		return "admin/user";
	}


	@RequestMapping(value = "/admin/admin")
	public String admin(Model model ,PageDTO pv) {
		List<AdminDTO> dtos = null;
		System.out.println("admin con");
		int totalCount = adminService.countAdminAllService(); 
		// 페이지네이션을 위한 결과 개수 가져오기
		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			model.addAttribute("pv", this.pdto); // 전체수에 맞춘 pv 추가
			model.addAttribute("number",pdto.getNumber());

		}
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
		dtos = adminService.printAdminService(this.pdto); // 전체 결과 리스트
		model.addAttribute("dtos", dtos); // 리스트 추가
		model.addAttribute("count",totalCount); //전체 수 추가
	
		System.out.println(dtos.size());
		
		return "admin/admin";
	}




	@RequestMapping(value = "/admin/borrow")
	public String borrow() {
		return "admin/borrow";
	}

	@RequestMapping(value = "/admin/review")
	public String review() {
		return "admin/review";
	}

	@RequestMapping(value = "/admin/deleteUser")
	public String deleteUser(@RequestParam("user_id") String user_id) {
		adminService.deleteUserService(user_id); //유저 아이디 삭제
		System.out.println(user_id);

		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/admin/deleteAdmin")
	public String deleteUser(@RequestParam("admin_id") String admin_id, HttpSession session) {
		adminService.deleteAdminService(admin_id); //관리자 탈퇴
		System.out.println(admin_id);
		session.invalidate(); // 탈퇴시 세션값 지우고 홈으로 리다이렉트
		return "redirect:/";
	}

	@RequestMapping(value = "/admin/searchUser", method = RequestMethod.GET)
	public String searchuser(Model model, PageDTO pv, @RequestParam("option") String option,
			@RequestParam("search") String search) {
		List<UserDTO> dtos = null;
		System.out.println(option + "_" + search);
		int totalCount = adminService.countUserService(search, option); 
		// 페이지네이션을 위한 결과 개수 가져오기
		System.out.println(totalCount); // 검색 결과 수 검색어와 옵션에 일치하는 데이터 수
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			model.addAttribute("searchResultPv", this.pdto); // 검색결과에 맞춘 pv 추가
			model.addAttribute("number",pdto.getNumber());

		}
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
		dtos = adminService.searchUserService(this.pdto, search, option);
		model.addAttribute("searchdtos", dtos); //검색결과 리스트 추가
		System.out.println("컨트롤러" + dtos.size());
		model.addAttribute("query",search); // 검색어 추가
		model.addAttribute("option",option); // 옵션 추가
		model.addAttribute("searchcount",totalCount);

		return "admin/user";
	}
	
	@RequestMapping(value = "/admin/searchAdmin", method = RequestMethod.GET)
	public String searchadmin(Model model, PageDTO pv, @RequestParam("option") String option,
			@RequestParam("search") String search) {
		List<AdminDTO> dtos = null;

		int totalCount = adminService.countAdminService(search, option);
		// 페이지네이션을 위한 결과 개수 가져오기
		System.out.println(totalCount); //검색 결과 수 
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			model.addAttribute("searchResultPv", this.pdto); // 검색 결과에 맞춘 pv 
			model.addAttribute("number",pdto.getNumber());

		}
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
		dtos = adminService.searchAdminService(this.pdto, search, option);
		model.addAttribute("searchdtos", dtos); // 검색 리스트 추가
		System.out.println("컨트롤러" + dtos.size()); 
		model.addAttribute("query",search); //검색어
		model.addAttribute("option",option); // 옵션 
		model.addAttribute("searchcount",totalCount); // 검색 결과 수
		return "admin/admin";
	}
	
	@RequestMapping(value = "admin/changepass", method = RequestMethod.POST)
	public String changepass(@RequestParam("newpass") String newpass, @RequestParam("admin_id") String admin_id,
			HttpSession session) {
		System.out.println("컨트롤러 실행");
		System.out.println(newpass);
		System.out.println(admin_id);
		adminService.changePassService(newpass, admin_id); //비밀번호 변경 수행
		session.invalidate(); //수행시 세션값 없애고 /admin 로그인 창으로 리다이렉트

		return "redirect:/admin";

	}

}
