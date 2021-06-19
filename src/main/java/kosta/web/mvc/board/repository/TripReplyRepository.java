package kosta.web.mvc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.board.domain.TripBoard;
import kosta.web.mvc.board.domain.TripReply;

public interface TripReplyRepository extends JpaRepository<TripReply, Long> {

}
