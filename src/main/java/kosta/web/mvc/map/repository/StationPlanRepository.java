package kosta.web.mvc.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.domain.Station;
import kosta.web.mvc.map.dto.StationPlan;

public interface StationPlanRepository extends JpaRepository<StationPlan, Integer>{

}
