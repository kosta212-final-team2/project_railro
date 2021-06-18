package kosta.web.mvc.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kosta.web.mvc.board.domain.InfoBoard;

public interface InfoBoardService {

	/**
	 * 전체검색
	 */
	List<InfoBoard> selectAll();
	
	/**
	 * 전체검색 - Page처리
	 * */
	Page<InfoBoard> selectAll(Pageable pageable);
	
	/**
	 * 등록
	 */
	void insert(InfoBoard board);
	
	/**
	 * 상세보기를 위한 글번호 검색
	 * 	: 조회수 증가함 state=true일 때.
	 */
	InfoBoard selectBy(Long infoBno, boolean state);
	
	/**
	 * 수정하기
	 */
	InfoBoard update(InfoBoard infoBoard);
}
