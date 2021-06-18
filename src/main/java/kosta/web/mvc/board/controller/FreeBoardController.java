package kosta.web.mvc.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.repository.FreeBoardRepository;
import kosta.web.mvc.board.service.FreeBoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/free")
public class FreeBoardController {

	@Autowired
	private final FreeBoardService freeService;
	
	@Autowired
	private FreeBoardRepository freeRepository;
	
	/**
	 * 글 목록 조회
	 */
	/*@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int nowPage) {
		System.out.println("call selectAll");
		//model.addAttribute("infoList", infoService.selectAll(pageable));
		
		Pageable pageable = PageRequest.of((nowPage-1), 10, Direction.DESC, "infoBno");
		
		Page<FreeBoard> pageList = freeService.selectAll(pageable);
		
		int blockCount=5;               
		int temp = (nowPage-1) % blockCount; // 시작 페이지 구하기
		int startPage = nowPage - temp;
		
		model.addAttribute("pageList", pageList); 
		
		model.addAttribute("blockCount", blockCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		
		System.out.println(pageList);
		
		return "page/board/free/list"; 
	}*/
	
    ////////////////////////
	/**
	 * 전체검색 또는 조건 검색
	 */
	@RequestMapping("/free")
	public String freeIdSearch(String keyword, String type, Model model, @RequestParam(defaultValue = "1") int nowPage) {

		Pageable pageable = PageRequest.of((nowPage - 1), 10, Direction.DESC, "freeBno");

		Page<FreeBoard> freeSearchList = null;

		if (keyword == null) {
			freeSearchList = freeService.selectAll(pageable);
		}

		else if (type.equals("subject")) {
			freeSearchList = freeService.freeSubjectSearch(keyword, pageable);
		} else if (type.equals("writer")) {
			freeSearchList = freeService.freeIdSearch(keyword, pageable);
		}

		System.out.println("freeSearchList.size() = " + freeSearchList.getContent().size());

		int blockCount=5;               
		int temp = (nowPage-1) % blockCount; // 시작 페이지 구하기
		int startPage = nowPage - temp;
		
		model.addAttribute("freeSearchList", freeSearchList);
		model.addAttribute("blockCount", blockCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("freeList", freeSearchList.getContent());

		return "page/board/free/list";
	}
	
	/**
	 * 글 등록폼
	 * */
	@RequestMapping("/write")
	public String write() {
		return "page/board/free/write";
	}
	
	/**
	 * 글 등록하기
	 * */
	@RequestMapping("/insert")
	public String insert(FreeBoard board) {
		
		//등록전에 입력한데이터에 유효하지 않는 특수문자,태그 등이 있는지 체크!! - filter 
		String content = board.getFreeContent().replace("<", "&lt;");
		board.setFreeContent(content);
		
		freeService.insert(board);
		
		return "redirect:/board/free/list";
	}
	
	/**
	 * 상세보기
	 */
	@RequestMapping("/read/{freeBno}")
	public ModelAndView read(@PathVariable Long freeBno, String flag) {
		boolean state = flag==null ? true : false;
		
		FreeBoard freeBoard = freeService.selectBy(freeBno, state);//state가 true이면 조회수증가, false 조회수 증가안함.
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page/board/free/read");
		mv.addObject("board", freeBoard);
		
		return mv;
	}

}



