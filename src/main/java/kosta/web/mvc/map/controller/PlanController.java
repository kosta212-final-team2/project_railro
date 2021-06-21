package kosta.web.mvc.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.map.service.PlanService;

@Controller
@RequestMapping("/map")
public class PlanController {
	@Autowired
	PlanService planService;
	@RequestMapping("/planData")
	public ModelAndView planData(Integer planId) {
		ModelAndView mv=new ModelAndView("page/map/planData", "planId", planId);
		return mv;
	}
	
	@RequestMapping("/planDelete")
	public String planDelete(Integer planId) {
		planService.deleteTravelPlan(planId);
		return "redirect:/member/mypage";
	}
}
