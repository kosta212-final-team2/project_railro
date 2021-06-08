package kosta.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call testpage");
		return "testpage"; // WEB-INF/views/testpage.jsp
	}
}
