package kosta.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call index");
		return "main/index"; // WEB-INF/views/index.jsp
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
