package kosta.web.mvc.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.dto.DetailedPlan;
import kosta.web.mvc.map.dto.StationPlan;

public interface DetailedPlanRepository  extends JpaRepository<DetailedPlan, Integer>{
	public List<DetailedPlan> findByStationPlan_stationPlanId(int stationPlanId);
}
