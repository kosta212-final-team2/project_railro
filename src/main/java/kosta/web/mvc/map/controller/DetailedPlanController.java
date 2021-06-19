package kosta.web.mvc.map.controller;

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
public class DetailedPlanController {
	@Autowired
	DetailedPlanService detailService;
	
	@RequestMapping("/map/test")
	public String test() {
		return "page/test";
	}
	
	@RequestMapping("/index")
	public String thymeleafTest() { 
		return "page/index"; 
	}
	
	@RequestMapping("/map/categorySearch")
	public ModelAndView main(Double centerLat, Double centerLng, Integer stationPlanNum ) {
		
		ModelAndView mv=new ModelAndView("page/map/map");
		mv.addObject("centerLat", centerLat);
		mv.addObject("centerLng", centerLng);
		mv.addObject("stationPlanNum", stationPlanNum);
		
		return mv;
	}
	
	@RequestMapping("/map/detailSave")
	public String detailSave(DetailedPlanList list) {
		System.out.println(list.getDetailedPlans());
		detailService.insertAll(list);
//		detailService.findByStaionPlan(99);
		return "page/map";
	}
	
	@RequestMapping("/map/updateForm")
	public ModelAndView updateForm(int stationPlanNum) {
		
		List<DetailedPlan> list= detailService.findByStaionPlan(stationPlanNum);
		
		ModelAndView mv=new ModelAndView("page/map/updateForm", "detailData", list);
		return mv;
	}
	
	@RequestMapping("/map/update")
	public String update(DetailedPlanList list, int stationPlanNum) {
		for(DetailedPlan dplan:list.getDetailedPlans()) {
			dplan.setStationPlan(new StationPlan(stationPlanNum));
		}
		detailService.updateAll(list, stationPlanNum);
		return "page/map";
	}
}
