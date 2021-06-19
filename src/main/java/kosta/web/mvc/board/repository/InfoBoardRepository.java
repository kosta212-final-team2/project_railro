package kosta.web.mvc.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.InfoBoard;

public interface InfoBoardRepository extends JpaRepository<InfoBoard, Long> {

	/**
	 * 조회수 증가
	 */
	@Query("update InfoBoard b set b.infoReadnum=b.infoReadnum+1 where b.infoBno=?1")
	@Modifying
	void readnumUpdate(Long infoBno);
	
	/**
	 * 제목검색
	 * */
	Page<InfoBoard> findByInfoSubjectContaining(String keyword, Pageable pageable);
	
	/**
	 * 작성자검색
	 * */
	Page<InfoBoard> findByMemberIdContaining(String keyword, Pageable pageable);

}
