package kosta.web.mvc.board.controller;

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
import kosta.web.mvc.board.service.FreeBoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class FreeBoardController {

	private final FreeBoardService freeService;
	
	/**
	 * 글 등록폼
	 * */
	@RequestMapping("/freewrite")
	public void write() {}
	
	/**
	 * 글 등록하기 
	 * */
	@RequestMapping("/insert")
	public String insert(FreeBoard board) {
		
		//등록전에 입력한데이터에 유효하지 않는 특수문자,태그 등이 있는지 체크!! - filter 
		String freeContent = board.getFreeContent().replace("<", "&lt;");
		board.setFreeContent(freeContent);
		
		freeService.insert(board);
		
		return "redirect:/board/freelist";
	}
	
	/**
	 * 글 목록 조회
	 */
	@RequestMapping("/free")
	public String list(@PageableDefault Pageable pageable, Model model) {
		System.out.println("call selectAll");
    model.addAttribute("freeList", freeService.selectAll(pageable));
		return "page/board/free/list"; 
	}
	
	/**
	 * 상세보기
	 * */
	@RequestMapping("/freeread/{freeBno}")
	public ModelAndView read(@PathVariable Long freeBno, String flag) {
		boolean state = flag==null ? true : false;
		
		FreeBoard freeBoard = freeService.selectBy(freeBno, state);//state가 true이면 조회수증가, false 조회수 증가안함.
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/freeread");
		mv.addObject("board", freeBoard);
		
		return mv;
	}
	
	/**
	 * 수정하기 폼
	 * */
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(Long bno) {
		FreeBoard board =  freeService.selectBy(bno, false);
		
		return new ModelAndView("board/update", "board", board);
	}
	
	
	/**
	 * 수정 완료
	 * */
	@RequestMapping("/update")
	public ModelAndView update(FreeBoard freeBoard) {//내용 비번, 제목, 글번호
		FreeBoard dbBoard = freeService.update(freeBoard);
		
		return new ModelAndView("free/read", "freeBoard", dbBoard);
	}
	
	/**
	 * 삭제하기 
	 * */
	@RequestMapping("/delete")
	public String delete(Long bno) {
		 freeService.delete(bno);
		 
		 return "redirect:/free/list";
	}
}



