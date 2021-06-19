package kosta.web.mvc.board.service;

import kosta.web.mvc.board.domain.TripReply;

public interface TripReplyService {
	/**
	 * 등록하기
	 */
	void insert(TripReply tripReply);
	
	/**
	 * 삭제
	 */
	void delete(Long tripRno);

}
