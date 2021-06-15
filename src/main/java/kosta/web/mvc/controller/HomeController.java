package kosta.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call testpage");
		return "page/index"; // resources/templates/page/index.html
	}
	
	@RequestMapping("/thymeleafTest")
	public String thymeleafTest() { 
		return "html/index"; 
	}
	
	@RequestMapping("/main")
	public String main() { 
		return "html/main"; 
	}

}
