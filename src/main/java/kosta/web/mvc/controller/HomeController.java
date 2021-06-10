package kosta.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call testpage");
		return "page/main"; // WEB-INF/views/testpage.jsp
	}
	
	@RequestMapping("/index")
	public String thymeleafTest() { 
		return "page/index"; 
	}
	
	@RequestMapping("/categorySearch")
	public String main() {
		
		return "page/map";
	}

}
