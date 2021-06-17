package kosta.web.mvc.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.dto.TravelPlan;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Integer> {

	List<TravelPlan> findAllByUserId(String memberId);

}
