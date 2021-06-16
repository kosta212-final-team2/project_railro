package kosta.web.mvc.map.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.dto.StationList;
import kosta.web.mvc.map.dto.StationPlan;

public interface StationService {
	
  /**
   * 전체검색
   * */
	List<Station> selectAll();
	
	
	/**
	 * 전체검색 - Page처리
	 * */
	Page<Station> selectAll(Pageable pageable);
	
	
	Station selectById(int id);
	
	List<Station> selectByKeyword(String keyword);
	
	
	void insertAll(StationList list);

}
