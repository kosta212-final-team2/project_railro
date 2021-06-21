package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		return "redirect:/board/info/read/"+infoBno+"?flag=1";
	}
	
	/**
	 * 댓글 수정 폼
	 */
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(Long infoRno, Long infoBno) {
		InfoReply infoReply = infoReplyService.selectBy(infoRno);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page/board/info/updateReply");
		mv.addObject("infoBno", infoBno);
		mv.addObject("reply", infoReply);
		
		return mv;
	}
	
	/**
	 * 댓글 수정하기
	 */
	@RequestMapping("/update")
	public String update(InfoReply infoReply, Long infoBno) {
		infoReplyService.update(infoReply);
		
		return "redirect:/board/info/read/"+infoBno+"?flag=1";
	}
	
	/**
	 * 댓글 삭제하기
	 */
	@RequestMapping("/delete")
	public String delete(Long infoRno, Long infoBno) {
		System.out.println("infoBno = "+infoBno+", infoRno = "+infoRno);
		infoReplyService.delete(infoRno);
		
		return "redirect:/board/info/read/"+infoBno+"?flag=1";
	}

}
