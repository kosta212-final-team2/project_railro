package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.board.service.InfoBoardService;
import kosta.web.mvc.repository.InfoBoardRepository;

@Controller
@RequestMapping("/board")
public class InfoBoardController {
	
	@Autowired
	private InfoBoardService infoService;
	
	@Autowired
	private InfoBoardRepository infoRepository;

	/**
	 * 글 목록 조회
	 */
	@RequestMapping("/info")
	public String list(@PageableDefault Pageable pageable, Model model) {
		System.out.println("call selectAll");
    model.addAttribute("infoList", infoService.selectAll(pageable));
		return "page/board/info/list"; 
	}
}
