package kosta.web.mvc.board.service;

import kosta.web.mvc.board.domain.InfoReply;

public interface InfoReplyService {
	/**
	 * 등록하기
	 */
	void insert(InfoReply infoReply);
	
	/**
	 * 삭제
	 */
	void delete(Long infoRno);

}
