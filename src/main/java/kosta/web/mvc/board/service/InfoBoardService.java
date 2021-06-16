package kosta.web.mvc.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kosta.web.mvc.domain.InfoBoard;

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
}
