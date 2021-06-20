package kosta.web.mvc.board.service;

import kosta.web.mvc.board.domain.InfoReply;

public interface InfoReplyService {
	/**
	 * 등록하기
	 */
	void insert(InfoReply infoReply);
	
	/**
	 * 수정하기를 위한 댓글번호 검색
	 */
	InfoReply selectBy(Long infoRno);
	
	/**
	 * 수정하기
	 */
	InfoReply update(InfoReply infoReply);
	
	/**
	 * 삭제
	 */
	void delete(Long infoRno);

}
