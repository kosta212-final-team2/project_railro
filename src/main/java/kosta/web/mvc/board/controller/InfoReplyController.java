package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.board.domain.InfoReply;
import kosta.web.mvc.board.service.InfoReplyService;
import kosta.web.mvc.member.domain.Member;

@Controller
@RequestMapping("/board/info/reply")
public class InfoReplyController {
	
	@Autowired
	private InfoReplyService infoReplyService;
	
	/**
	 * 댓글 등록하기
	 */
	@RequestMapping("/insert")
	public String insert(InfoReply infoReply, Long infoBno, String memberId) {
		infoReply.setInfoBoard(new InfoBoard(infoBno));
		infoReply.setMember(new Member(memberId));
		
		infoReplyService.insert(infoReply);
		
		// flag : 조회수를 증가하지 않게 하기 위함
		return "redirect:/board/info/read/"+infoBno;
	}

}
