package kosta.web.mvc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.board.domain.FreeReply;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Long> {

}
