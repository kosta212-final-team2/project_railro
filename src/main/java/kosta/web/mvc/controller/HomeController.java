package kosta.web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.map.dto.DetailedPlan;
import kosta.web.mvc.map.dto.DetailedPlanList;
import kosta.web.mvc.map.dto.StationPlan;
import kosta.web.mvc.map.service.DetailedPlanService;

@Controller
public class HomeController {
	@Autowired
	DetailedPlanService detailService;
	
	@RequestMapping("/test")
	public String test() {
		return "page/test";
	}
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
		detailService.insertAll(list);
//		detailService.findByStaionPlan(99);
		return "page/map";
	}
	
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(int stationPlanNum) {
		
		List<DetailedPlan> list= detailService.findByStaionPlan(stationPlanNum);
		
		ModelAndView mv=new ModelAndView("page/updateForm", "detailData", list);
		return mv;
	}
	
	@RequestMapping("/update")
	public String update(DetailedPlanList list, int stationPlanNum) {
		for(DetailedPlan dplan:list.getDetailedPlans()) {
			dplan.setStationPlan(new StationPlan(stationPlanNum));
		}
		detailService.updateAll(list, stationPlanNum);
		return "page/map";
	}

}
