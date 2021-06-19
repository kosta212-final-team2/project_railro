package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.FreeReply;
import kosta.web.mvc.board.service.FreeReplyService;
import kosta.web.mvc.member.domain.Member;

@Controller
@RequestMapping("/board/free/reply")
public class FreeReplyController {
	
	@Autowired
	private FreeReplyService freeReplyService;
	
	/**
	 * 댓글 등록하기
	 */
	@RequestMapping("/insert")
	public String insert(FreeReply freeReply, Long freeBno, String memberId) {
		freeReply.setFreeBoard(new FreeBoard(freeBno));
		freeReply.setMember(new Member(memberId));
		
		freeReplyService.insert(freeReply);
		
		// flag : 조회수를 증가하지 않게 하기 위함
		return "redirect:/board/free/read/"+freeBno;
	}
	
}
