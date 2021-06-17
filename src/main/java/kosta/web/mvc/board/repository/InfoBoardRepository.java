package kosta.web.mvc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.board.domain.InfoBoard;

public interface InfoBoardRepository extends JpaRepository<InfoBoard, Long> {

}
