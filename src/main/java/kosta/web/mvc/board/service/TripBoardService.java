package kosta.web.mvc.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kosta.web.mvc.board.domain.TripBoard;

public interface TripBoardService {

	/**
	 * 전체검색
	 */
	List<TripBoard> selectAll();
	
	/**
	 * 전체검색 - Page처리
	 * */
	Page<TripBoard> selectAll(Pageable pageable);
	
	/**
	 * 제목으로 검색
	 */
	Page<TripBoard> tripSubjectSearch(String keyword, Pageable pageable);
	
	/**
	 * 작성자로 검색
	 */
	Page<TripBoard> tripIdSearch(String keyword, Pageable pageable);
	
	/**
	 * 등록
	 */
	void insert(TripBoard board);
	
	/**
	 * 상세보기를 위한 글번호 검색
	 * 	: 조회수 증가함 state=true일 때.
	 */
	TripBoard selectBy(Long tripBno, boolean state);
	
	/**
	 * 수정하기
	 */
	TripBoard update(TripBoard tripBoard);
	
	/**
	 * 삭제하기
	 */
	void delete(Long tripBno);
}
