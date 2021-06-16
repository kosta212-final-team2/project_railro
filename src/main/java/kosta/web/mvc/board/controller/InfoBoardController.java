package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.board.service.InfoBoardService;
import kosta.web.mvc.domain.InfoBoard;
import kosta.web.mvc.repository.InfoBoardRepository;

@Controller
@RequestMapping("/board/info")
public class InfoBoardController {
	
	@Autowired
	private InfoBoardService infoService;
	
	@Autowired
	private InfoBoardRepository infoRepository;

	/**
	 * 글 목록 조회
	 */
	@RequestMapping("/list")
	public String list(@PageableDefault Pageable pageable, Model model) {
		System.out.println("call selectAll");
    model.addAttribute("infoList", infoService.selectAll(pageable));
		return "page/board/info/list"; 
	}
	
	/**
	 * 글 등록폼
	 * */
	@RequestMapping("/write")
	public String write() {
		return "page/board/info/write";
	}
	
	/**
	 * 글 등록하기
	 * */
	@RequestMapping("/insert")
	public String insert(InfoBoard board) {
		
		//등록전에 입력한데이터에 유효하지 않는 특수문자,태그 등이 있는지 체크!! - filter 
		String content = board.getInfoContent().replace("<", "&lt;");
		board.setInfoContent(content);
		
		infoService.insert(board);
		
		return "redirect://page/board/info/list";
		
	}
}
