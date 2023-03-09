package admin.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import admin.dao.AdminDAO;
import admin.dto.AdminDTO;
import admin.dto.adminAuthInfo;
import admin.service.AdminService;
import admin.dto.adminAuthInfo;
import admin.dto.AdminDTO;

//	http://localhost:8090/myapp/admin/adminSignup

@Controller
public class AdminController {

	private AdminService adminService;

	public AdminController() {

	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	// 관리자 회원가입 폼
	@RequestMapping(value="/admin/adminSignup", method=RequestMethod.GET)
	public ModelAndView addAdmin(ModelAndView mav) {
		mav.setViewName("admin/adminSignup");
		return mav; 
};
	
	// 관리자 회원가입 후 홈페이지로 redirect
	@RequestMapping(value="/admin/adminSignup", method=RequestMethod.POST)
	public String addAdmin(AdminDTO dto, HttpSession session) {
		adminAuthInfo authInfo = adminService.addAdminProcess(dto);
		session.setAttribute("authInfo", authInfo);
		return "redirect:/home";
	}

	// 사용자 로그인
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String loginUser() {
		return "admin/login";
	}

	// 로그인 처리
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String loginAdmin(AdminDTO dto, HttpSession session, HttpServletResponse resp) {

		try {
			adminAuthInfo authInfo = adminService.loginProcess(dto);
			session.setAttribute("authInfo", authInfo);
			System.out.println(authInfo);
			return "redirect:/home";
		} catch (Exception e) {
			resp.setContentType("text/html;charset=UTF-8");
			try {
				PrintWriter out = resp.getWriter();
				out.print("<script>alert('아이디 비밀번호 불일치'); history.go(-1);</script>");
				out.flush();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}

	@RequestMapping(value="adminSignup/idcheck", method=RequestMethod.POST)
	@ResponseBody
	public String idcheck(@RequestParam("adminId") String adminId) {
	    String idcheck = "N";
	    if(adminService.idcheck(adminId)>0) {
	        idcheck="Y";
	    }
	    System.out.println(idcheck);
	    return idcheck;
	}
	// 로그아웃
	@RequestMapping(value = "/user/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}

}