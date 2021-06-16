package kosta.web.mvc.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.map.dto.DetailedPlan;
import kosta.web.mvc.map.dto.StationPlan;

public interface DetailedPlanRepository  extends JpaRepository<DetailedPlan, Integer>{
	public List<DetailedPlan> findByStationPlan_stationPlanId(int stationPlanId);
	public List<DetailedPlan> findByStationPlan_stationPlanIdOrderByDetailedOrder(int stationPlanId);
	@Query("delete from DetailedPlan where station_plan_num=?1")
	@Modifying // DDL , DML할때 필수 
	public void deleteByStationNum(int sno);
}
