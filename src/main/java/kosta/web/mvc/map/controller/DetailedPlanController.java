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
import kosta.web.mvc.map.service.StationService;

@Controller
public class DetailedPlanController {
	@Autowired
	DetailedPlanService detailService;
	
	@Autowired
	StationService stationService;
	
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
	public ModelAndView updateForm(Double centerLat, Double centerLng, int stationPlanNum) {
		
		List<DetailedPlan> list= detailService.findByStaionPlan(stationPlanNum);
		
		
		ModelAndView mv=new ModelAndView("page/map/updateForm", "detailData", list);
		mv.addObject("centerLat", centerLat);
		mv.addObject("centerLng", centerLng);
		mv.addObject("stationPlanNum", stationPlanNum);
		return mv;
	}
	
	@RequestMapping("/map/update")
	public ModelAndView update(DetailedPlanList list, Integer stationPlanNum) {
		for(DetailedPlan dplan:list.getDetailedPlans()) {
			dplan.setStationPlan(new StationPlan(stationPlanNum));
		}
		
		detailService.updateAll(list, stationPlanNum);
		
		StationPlan stationPlan=stationService.findByStationPlanId(stationPlanNum);
		
		List<StationPlan> stationList =stationService.selectPlanByPlanNum(stationPlan.getTravelPlan().getPlanId());
		
		
		System.out.println(stationList);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapjo/plan");
		mv.addObject("stationUpdate", stationList);
		mv.addObject("planId", stationList.get(0).getTravelPlan().getPlanId());
		return mv;
	}
}
