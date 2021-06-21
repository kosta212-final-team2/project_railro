package kosta.web.mvc.map.service;

import java.util.List;

import kosta.web.mvc.map.dto.TravelPlan;
import kosta.web.mvc.map.repository.TravelPlanRepository;

public interface PlanService {
	public List<TravelPlan> getTravelPlanByUser(String memberId);
	
	public TravelPlan getTravelPlanById(int planId);
	
	
	void insertTravelPlan(TravelPlan plan);
	
	void deleteTravelPlan(int planId);
}
