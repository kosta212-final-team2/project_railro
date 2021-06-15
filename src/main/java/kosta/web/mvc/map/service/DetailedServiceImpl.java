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
		System.out.println("다다다");
		List<DetailedPlan> list= detailRepository.findByStationPlan_stationPlanId(i);
		System.out.println("다다다2");

		System.out.println(list);
		System.out.println("다다다3");

		return null;
	}
	
}
