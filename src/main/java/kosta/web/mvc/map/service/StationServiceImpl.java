package kosta.web.mvc.map.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.dto.StationList;
import kosta.web.mvc.map.dto.StationPlan;
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
		System.out.println(list.getList().size());
	 System.out.println(list.getList());
		//stationPlanRepository.saveAll(list.getList());
		for (StationPlan s : list.getList()) {
			if (s.getTravelDate() != null) {
				stationPlanRepository.save(s);
			}
		}
	}

	/**
	 * stationPlan 수정하기 위해서 데이터 가져가기 
	 * */
	@Override
	public List<StationPlan> selectPlanByPlanNum(int planId) {
		return stationPlanRepository.findByTravelPlan_planId(planId);
	}

	
	/**
	 * 수정하기 위해서 station_plan_id 로 개별 역 찾기 
	 * */
	@Override
	public StationPlan findByStationPlanId(int planId) {
		return stationPlanRepository.findByStationPlanId(planId);
	}

	/**
	 * 역 일정 수정하기 
	 * */
	@Override
	public void updateAll(StationList list) {
		for (StationPlan s : list.getList()) {
			if(s.getTravelDate() != null) {
				
				if (s.getStationPlanId() != 0) {
					StationPlan plan = stationPlanRepository.findByStationPlanId(s.getStationPlanId());
					System.out.println(plan.getStationPlanId());
					System.out.println(s.getStationPlanId());
//			System.out.println(plan.getTravelOrder());
					plan.setTravelDate(s.getTravelDate());
					plan.setTravelOrder(s.getTravelOrder());
					
					stationPlanRepository.save(plan);
				}
				
				if(s.getStationPlanId() == 0) {
					stationPlanRepository.save(s);
				}
				
				
				
				
			}

		}//for
//		List<StationPlan> dbList = stationPlanRepository.findByTravelPlan_planId(list.getList().get(0).getTravelPlan().getPlanId());
//		for(StationPlan plan :dbList) {
//			if(!list.getList().contains(plan)) {
//				stationPlanRepository.delete(plan);
//			}
//		}

	}

	
	
	
	
	

}
