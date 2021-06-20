package kosta.web.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.map.dto.DetailedPlan;
import kosta.web.mvc.map.dto.DetailedPlanList;
import kosta.web.mvc.map.dto.StationPlan;
import kosta.web.mvc.map.service.DetailedPlanService;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call testpage");

		return "page/index"; // resources/templates/page/index.html
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		return "page/loginSuccess";
	}

	@RequestMapping("/about")
	public String aboutPage() {
		return "page/introduce/about";
	}

}
