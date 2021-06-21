package kosta.web.mvc.map.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.map.dto.TravelPlan;
import kosta.web.mvc.map.repository.TravelPlanRepository;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	@Autowired
	TravelPlanRepository planRepository;
	@Override
	public List<TravelPlan> getTravelPlanByUser(String userId) {
		return planRepository.findAllByUserId(userId);
	}

	@Override
	public TravelPlan getTravelPlanById(int planId) {
		
		return planRepository.findById(planId).orElse(null);
	}

}
