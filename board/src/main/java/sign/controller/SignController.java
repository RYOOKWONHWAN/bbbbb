package sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost:8090/myapp/login

@Controller
public class SignController {
	
	@RequestMapping(value = "login/")
	public String login() {
		return "login/";
	}
	
	
}

