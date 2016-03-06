package openu.ibdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutingController {

	@RequestMapping("/")
	public String home() {
		return "index.html";
	}
	
//	@RequestMapping("/login")
//	public String login() {
//		return "views/login.html";
//	}
//	
//	@RequestMapping("/home")
//	public String home() {
//		return "views/index.html";
//	}

}
