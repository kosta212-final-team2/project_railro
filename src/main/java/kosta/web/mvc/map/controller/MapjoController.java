package kosta.web.mvc.map.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.dto.Item;
import kosta.web.mvc.map.dto.StationList;
import kosta.web.mvc.map.dto.StationPlan;
import kosta.web.mvc.map.dto.TravelPlan;
import kosta.web.mvc.map.service.PlanService;
import kosta.web.mvc.map.service.StationService;

@Controller
@RequestMapping("/mapjo")
public class MapjoController {
	@Autowired
	private StationService stationService;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private XmlParsingTest parsing;
	
	/**
	 * naver 지도 보여주기 / test 용 
	 * */
	@RequestMapping("/list/{id}")
	public ModelAndView list(@PathVariable int id) {
		
		Station stationDetail = stationService.selectById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapjo/read2");
		mv.addObject("stationDetail", stationDetail);
		return mv;
		
	}
	/**
	 * 역리스트 보여주기 / test 용 
	 * */
	@RequestMapping("/stationList")
	public void stationList(Model model, @RequestParam(defaultValue = "0")int nowPage) {
		Pageable pageable = PageRequest.of(nowPage, 10, Direction.ASC, "id");
		Page<Station> stationList = stationService.selectAll(pageable);
		//List<Item> infoList = parsing.test();
		model.addAttribute("stationList",stationList); // view -> ${stationList.메서드이름}
		//model.addAttribute("infoList",infoList); // view -> ${stationList.메서드이름}
	}
	
	
	/**
	 * 열차정보 보여주기  / test 용 
	 * */
	@RequestMapping("/infoList")
	public void infoList(Model model) {
		List<Item> infoList = parsing.test();
		
		model.addAttribute("infoList",infoList); 
	}
	
	/**
	 * search by keyword / test 용 
	 * */
	@RequestMapping("/searchkakao")
	public void searchkakao() {
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * kakao 지도 역 검색 및 마커 찍기 
	 * */
	@RequestMapping("/stationMarker")
	public ModelAndView stationMarker(TravelPlan plan) {
		
//		System.out.println(plan.getPlanName());
//		System.out.println(plan.getStartDate());
//		System.out.println(plan.getEndDate());
		System.out.println("===================");
		System.out.println("userId="+plan.getUserId());
		System.out.println("===================");
			planService.insertTravelPlan(plan);

			ModelAndView mv = new ModelAndView();
			mv.addObject("travelPlan", plan);
			return mv;
	}
	
	/**
	 * save city plan
	 * */
	@RequestMapping("/citySave")
	public ModelAndView citySave (StationList station) {
//		System.out.println("나와");
//		System.out.println(station.getList());
//		for(StationPlan s:station.getList()) {
//			System.out.println(s.getStationPlanId()+" , " + s.getTrainStation()+","+ s.getTravelDate());
//		}
		
		stationService.insertAll(station);
		
		System.out.println("============================");
		System.out.println("plandId="+station.getList().get(0).getTravelPlan().getPlanId());
		System.out.println("============================");
		List<StationPlan> list = stationService.selectPlanByPlanNum(station.getList().get(0).getTravelPlan().getPlanId());
		ModelAndView mv = new ModelAndView();
			mv.setViewName("mapjo/plan");
			mv.addObject("stationUpdate", list);
			mv.addObject("planId", station.getList().get(0).getTravelPlan().getPlanId());
		return mv;
		
	}
	/**
	 * update city plan
	 * */
	@RequestMapping("/cityUpdateForm")
	public ModelAndView cityUpdateForm (int planId) {
		List<StationPlan> list = stationService.selectPlanByPlanNum(planId);
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("mapjo/cityUpdateForm");
			mv.addObject("stationUpdate", list);
//		
		
			//ModelAndView mv = new ModelAndView();
			mv.setViewName("mapjo/cityUpdateForm");
			mv.addObject("planId", planId);
		return mv;
	}
	
	@RequestMapping("/cityUpdateForm2")
	@ResponseBody
	public List<StationPlan> cityUpdateForm2 (int planId) {
		List<StationPlan> list = stationService.selectPlanByPlanNum(planId);
		return list;
	}
	
	
	/**
	 * update city plan
	 * */
	@RequestMapping("/cityUpdate")
	public ModelAndView cityUpdate (StationList list,Integer planId) {
		System.out.println("list="+list.getList());
		
		stationService.updateAll(list, planId);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapjo/cityUpdateForm");
		mv.addObject("planId", planId);
		return mv;

	}
	
	
	/**
	 * travel plan page
	 * */
	@RequestMapping("/travelPlan")
	public void travelPlan () {
	}
	
	

}
