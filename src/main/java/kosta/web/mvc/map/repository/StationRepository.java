package kosta.web.mvc.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.map.domain.Station;

public interface StationRepository extends JpaRepository<Station, Integer>{


}
