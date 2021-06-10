package kosta.web.mvc.map.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.service.StationService;

@Controller
@RequestMapping("/map")
public class MapjoController {
	@Autowired
	private StationService stationService;
	
	/**
	 * naver 지도 보여주기 
	 * */
	@RequestMapping("/list/{id}")
	public ModelAndView list(@PathVariable int id) {
		
		Station stationDetail = stationService.selectById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("map/read");
		mv.addObject("stationDetail", stationDetail);
		return mv;
		
	}
	/**
	 * 역리스트 보여주기 
	 * */
	@RequestMapping("/stationList")
	public void stationList(Model model, @RequestParam(defaultValue = "0")int nowPage) {
		Pageable pageable = PageRequest.of(nowPage, 10, Direction.ASC, "citycode");
		Page<Station> stationList = stationService.selectAll(pageable);
		model.addAttribute("stationList",stationList); // view -> ${stationList.메서드이름}
	}
	
//	/**
//	 * 역전체 지도에 마커로 표시 
//	 * */
//	@RequestMapping("/mapTotal")
//	public void mapTotal(Model model) {
//		List<Station> list = stationService.selectAll();
//		model.addAttribute("list",list); // view -> ${list.메서드이름}
//	}
//	

}
