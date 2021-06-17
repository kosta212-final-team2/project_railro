package kosta.web.mvc.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.dto.StationPlan;

public interface StationPlanRepository extends JpaRepository<StationPlan, Integer>{

	List<StationPlan> findByTravelPlan_planId(int planId);
}
