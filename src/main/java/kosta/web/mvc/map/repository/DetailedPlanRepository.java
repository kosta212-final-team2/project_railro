package kosta.web.mvc.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.dto.DetailedPlan;

public interface DetailedPlanRepository  extends JpaRepository<DetailedPlan, Integer>{

}
