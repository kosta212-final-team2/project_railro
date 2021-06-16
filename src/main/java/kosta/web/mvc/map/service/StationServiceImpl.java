package kosta.web.mvc.map.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.dto.StationList;
import kosta.web.mvc.map.repository.StationPlanRepository;
import kosta.web.mvc.map.repository.StationRepository;
@Service
@Transactional
public class StationServiceImpl implements StationService {
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private StationPlanRepository stationPlanRepository;
	
	@Override
	public List<Station> selectAll() {
		return stationRepository.findAll();
	}

	@Override
	public Page<Station> selectAll(Pageable pageable) {
		return stationRepository.findAll(pageable);
	}

	@Override
	public Station selectById(int id) {
		return stationRepository.findById(id).orElse(null);
	}

	@Override
	public List<Station> selectByKeyword(String keyword) {
		
		
		return stationRepository.findByStationContaining(keyword);
	}
	
	@Override
	public void insertAll(StationList list) {
		stationPlanRepository.saveAll(list.getList());
	}

}
