package kosta.web.mvc.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
