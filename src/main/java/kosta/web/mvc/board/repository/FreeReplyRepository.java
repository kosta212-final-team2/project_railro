package kosta.web.mvc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.board.domain.FreeReply;

public interface FreeReplyRepository extends JpaRepository<FreeReply, Long> {

	@Query("delete from FreeReply r where r.freeRno=?1")
	@Modifying
	void deleteTest(Long freeRno);
}
