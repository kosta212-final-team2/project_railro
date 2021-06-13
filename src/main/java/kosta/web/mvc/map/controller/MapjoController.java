package kosta.web.mvc.map.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.dto.Item;
import kosta.web.mvc.map.service.StationService;

@Controller
@RequestMapping("/mapjo")
public class MapjoController {
	@Autowired
	private StationService stationService;
	
	@Autowired
	private XmlParsingTest parsing;
	
	/**
	 * naver 지도 보여주기 
	 * */
	@RequestMapping("/list/{id}")
	public ModelAndView list(@PathVariable int id) {
		
		Station stationDetail = stationService.selectById(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mapjo/read");
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
		//List<Item> infoList = parsing.test();
		model.addAttribute("stationList",stationList); // view -> ${stationList.메서드이름}
		//model.addAttribute("infoList",infoList); // view -> ${stationList.메서드이름}
	}
	
	
	/**
	 * 열차정보 보여주기  
	 * */
	@RequestMapping("/infoList")
	public void infoList(Model model) {
		List<Item> infoList = parsing.test();
		
		model.addAttribute("infoList",infoList); 
	}
	
	/**
	 * search by keyword
	 * */
	@RequestMapping("/searchkakao")
	public void searchkakao() {
		
	}
	
	/**
	 * search by keyword
	 * */
	@RequestMapping("/myro")
	public void myro() {
		
	}
	/**
	 * search by keyword
	 * */
	@RequestMapping("/myro2")
	public void myro2() {
		
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
