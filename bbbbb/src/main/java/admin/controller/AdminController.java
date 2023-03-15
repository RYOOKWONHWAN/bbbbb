package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import admin.service.AdminService;

//http://localhost:8090/myapp/admin

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String main(HttpSession session) {
		if(session.getAttribute("authInfo")!=null) {
			System.out.println("authInfo존재");
			return "redirect:/home";
		}
		return "admin";
	}
	@RequestMapping(value="/admin/login", method= RequestMethod.GET)
	public String login(HttpSession session) {
		if(session.getAttribute("authInfo")!=null) {
			System.out.println("authInfo존재");
			return "redirect:/home";
		}
		return "redirect:/home";
	}
	
	@RequestMapping(value="/admin/adminSignup", method= RequestMethod.GET)
	public String signup(HttpSession session) {
		if(session.getAttribute("authInfo")!=null) {
			System.out.println("authInfo존재");
			return "redirect:/home";
		}
		return "redirect:/home";
	}
	@RequestMapping(value = "/admin/adminSignup", method = RequestMethod.POST)
	public String processAdminSignup(AdminDTO dto, HttpSession session) {
		// 회원가입 처리 로직
		adminAuthInfo adminauthInfo = adminService.addAdminProcess(dto);
		session.setAttribute("adminauthInfo", adminauthInfo);

		return "admin/user"; // 회원가입 후 이동할 페이지
	}

	@RequestMapping(value = "admin/login", method = RequestMethod.POST)
	public String processLogin(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw,
		HttpSession session, HttpServletResponse resp) {

		
		adminAuthInfo login = new adminAuthInfo(loginId, loginPw);
		AdminDTO dto = adminService.loginProcess(login);
		if (dto != null) {
			session.setAttribute("adminauthInfo", login);
			return "redirect:/admin/user";
		} else {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out;
			try {
				out = resp.getWriter();
				out.print("<script>alert('아이디 비밀번호 불일치'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "admin/user"; // 로그인 후 이동할 페이지
	}
	// 로그아웃

	@RequestMapping(value = "/admin/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/admin";
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

	@RequestMapping(value = "/admin/user")
	public String user() {
		return "admin/user";
	}

	@RequestMapping(value = "/admin/admin")
	public String admin() {
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/notice")
	public String notice() {
		return "admin/notice";
	}

	@RequestMapping(value = "/admin/books")
	public String books() {
		return "admin/books";
	}

	@RequestMapping(value = "/admin/borrow")
	public String borrow() {
		return "admin/borrow";
	}

	@RequestMapping(value = "/admin/review")
	public String review() {
		return "admin/review";
	}

	@RequestMapping(value = "/admin/request")
	public String request() {
		return "admin/request";
	}

}
