package kosta.web.mvc.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.map.dto.TravelPlan;

public interface TravelPlanRepository extends JpaRepository<TravelPlan, Integer> {
	

	@Query(value="SELECT * FROM TRAVEL_PLAN t WHERE t.USER_ID=?1", nativeQuery = true)
	List<TravelPlan> findAllByUserId(String userId);

}
