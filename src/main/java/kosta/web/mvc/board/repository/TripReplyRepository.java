package kosta.web.mvc.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.board.domain.TripReply;

public interface TripReplyRepository extends JpaRepository<TripReply, Long> {

	@Query("delete from TripReply r where r.tripRno=?1")
	@Modifying
	void deleteTest(Long tripRno);
}
