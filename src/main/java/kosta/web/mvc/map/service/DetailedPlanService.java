package kosta.web.mvc.map.service;

import java.util.List;

import kosta.web.mvc.map.dto.DetailedPlan;
import kosta.web.mvc.map.dto.DetailedPlanList;

public interface DetailedPlanService {
	public void insertAll(DetailedPlanList list);
	
	public void insert(DetailedPlan dplan);

	public void update(DetailedPlan dplan);
	
	public List<DetailedPlan> findByStaionPlan(int i);
	
	public void updateAll(DetailedPlanList list,int stationPlanNum);

}
