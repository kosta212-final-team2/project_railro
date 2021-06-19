package kosta.web.mvc.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.board.domain.TripBoard;

public interface TripBoardRepository extends JpaRepository<TripBoard, Long> {

	/**
	 * 조회수 증가
	 */
	@Query("update TripBoard b set b.tripReadnum=b.tripReadnum+1 where b.tripBno=?1")
	@Modifying
	void readnumUpdate(Long tripBno);
	
	/**
	 * 제목검색
	 * */
	Page<TripBoard> findByTripSubjectContaining(String keyword, Pageable pageable);
	
	/**
	 * 작성자검색
	 * */
	Page<TripBoard> findByMemberIdContaining(String keyword, Pageable pageable);

}
