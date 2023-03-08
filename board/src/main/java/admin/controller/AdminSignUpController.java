package admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.service.MemberService;
import member.dto.AuthInfo;
import member.dto.MembersDTO;


// http://localhost:8090/myapp/admin

@Controller
public class AdminSignUpController {
	private MemberService memberService;
	public AdminSignUpController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}



	@RequestMapping(value = "admin/",method = RequestMethod.GET)
	public String admin() {
		
		return "admin/";
	}
	@RequestMapping(value="admin/",method= RequestMethod.POST)
	public String admin(MembersDTO memberDTO, HttpSession session) {
		AuthInfo authInfo = memberService.addMemberProcss(memberDTO);
		session.setAttribute("authInfo", authInfo);
		System.out.println("아이디"+memberDTO.getId());

		return "redirect:/myapp/";
	}
	
	
}

