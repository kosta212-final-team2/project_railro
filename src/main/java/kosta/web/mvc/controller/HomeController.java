package kosta.web.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call testpage");
		return "page/index"; // WEB-INF/views/testpage.jsp
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		return "page/loginSuccess";
	}

}
