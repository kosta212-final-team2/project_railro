package kosta.web.mvc.board.service;


import kosta.web.mvc.board.domain.FreeReply;

public interface FreeReplyService {

	/**
	* 등록하기
	* */
	void insert(FreeReply freeReply);

	/**
	 * 삭제
	 * */
	void delete(Long id);
}
