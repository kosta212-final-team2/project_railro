package kosta.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.map.dto.DetailedPlanList;

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
	
	@RequestMapping("/detailSave")
	public String detailSave(DetailedPlanList list) {
		System.out.println(list.getDetailedPlans());
		
		return "page/map";
	}

}
