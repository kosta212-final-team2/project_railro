package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.board.domain.TripBoard;
import kosta.web.mvc.board.domain.TripReply;
import kosta.web.mvc.board.service.TripReplyService;
import kosta.web.mvc.member.domain.Member;

@Controller
@RequestMapping("/board/trip/reply")
public class TripReplyController {
	
	@Autowired
	private TripReplyService tripReplyService;
	
	/**
	 * 댓글 등록하기
	 */
	@RequestMapping("/insert")
	public String insert(TripReply tripReply, Long tripBno, String memberId) {
		tripReply.setTripBoard(new TripBoard(tripBno));
		tripReply.setMember(new Member(memberId));
		
		tripReplyService.insert(tripReply);
		
		// flag : 조회수를 증가하지 않게 하기 위함
		return "redirect:/board/trip/read/"+tripBno+"?flag=1";
	}
	
	/**
	 * 댓글 삭제하기
	 */
	@RequestMapping("/delete")
	public String delete(Long tripRno, Long tripBno) {
		System.out.println("tripBno = "+tripBno+", tripRno = "+tripRno);
		tripReplyService.delete(tripRno);
		
		return "redirect:/board/trip/read/"+tripBno+"?flag=1";
	}

}
