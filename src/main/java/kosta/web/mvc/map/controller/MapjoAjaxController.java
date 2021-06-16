package kosta.web.mvc.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.service.StationService;

@RestController
public class MapjoAjaxController {
	@Autowired
	private StationService stationService;
	
	
	@RequestMapping(value = "/mapTotal")
	public String list() {
		return "redirect:/mapTotal";
	}
	
	
	@RequestMapping(value = "/markerTotal")
	public List<Station> total() {
		
		List<Station> list = stationService.selectAll();
//		for(Station station :list) {
//			System.out.println(station.getLat()+","+station.getLng());
//		}
		return list;
	}
	
	
	/**
	 * 역검색해서 리스트로 보여주기 
	 * */
	@RequestMapping(value = "/stationMarker")
	public List<Station> stationMarker(String keyword) {
		
		List<Station> list = stationService.selectByKeyword(keyword);

		return list;
	}
	
	
	
	@RequestMapping(value= "/searchByKeyword")
	public String searchByKeyword(String keyword) {
		
		
		System.out.println(keyword);
		return "right?";
	}

}
