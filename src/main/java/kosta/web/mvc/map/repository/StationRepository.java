package kosta.web.mvc.map.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.domain.Station;

public interface StationRepository extends JpaRepository<Station, Integer>{

	

	//select * from station where station like '%부산%';
	//@Query("select s from Station s where s.station like %:keyword%")
	List<Station> findByStationContaining(String keyword);
	//List<Station> findByStationLike(String keyword);

}
