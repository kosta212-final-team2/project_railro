package kosta.web.mvc.map.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.map.dto.DetailedPlan;
import kosta.web.mvc.map.dto.DetailedPlanList;
import kosta.web.mvc.map.repository.DetailedPlanRepository;

@Service
@Transactional
public class DetailedServiceImpl implements DetailedPlanService {

	@Autowired
	DetailedPlanRepository detailRepository;

	@Override
	public void insertAll(DetailedPlanList list) {
		// TODO Auto-generated method stub
		
		detailRepository.saveAll(list.getDetailedPlans());

	}

	@Override
	public void insert(DetailedPlan dplan) {
		// TODO Auto-generated method stub
		detailRepository.save(dplan);

	}

	@Override
	public void update(DetailedPlan dplan) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DetailedPlan> findByStaionPlan(int i) {
		// TODO Auto-generated method stub

		List<DetailedPlan> list = detailRepository.findByStationPlan_stationPlanIdOrderByDetailedOrder(i);
		return list;
	}

	@Override
	public void updateAll(DetailedPlanList list, int stationPlanNum) {
		// TODO Auto-generated method stub
		if (list.getDetailedPlans().size() > 0) {
			
			detailRepository.deleteByStationNum(stationPlanNum);
			detailRepository.saveAll(list.getDetailedPlans());
		}
	}

}
