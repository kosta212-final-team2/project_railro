package kosta.web.mvc.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/map")
public class PlanController {
	
	@RequestMapping("/planData")
	public ModelAndView planData(Integer planId) {
		ModelAndView mv=new ModelAndView("page/map/planData", "planId", planId);
		return mv;
	}
}
