package kosta.web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.domain.InfoBoard;

public interface InfoBoardRepository extends JpaRepository<InfoBoard, Long> {
 
}
