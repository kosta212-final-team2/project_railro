package kosta.web.mvc.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/home")
	public String adminHomePage() {
		return "page/admin/home";
	}
}
