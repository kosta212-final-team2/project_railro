package kosta.web.mvc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.board.domain.InfoReply;

public interface InfoReplyRepository extends JpaRepository<InfoReply, Long> {

	@Query("delete from InfoReply r where r.infoRno=?1")
	@Modifying
	void deleteTest(Long infoRno);
	
}
