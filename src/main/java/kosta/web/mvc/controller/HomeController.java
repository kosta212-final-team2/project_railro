package kosta.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.map.dto.DetailedPlanList;
import kosta.web.mvc.map.service.DetailedPlanService;

@Controller
public class HomeController {
	@Autowired
	DetailedPlanService detailService;
	
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
//		System.out.println(list.getDetailedPlans());
//		detailService.insertAll(list);
		detailService.findByStaionPlan(99);
		return "page/map";
	}

}
