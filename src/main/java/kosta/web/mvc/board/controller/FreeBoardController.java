package kosta.web.mvc.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
	 * 전체 목록 가져오기
	 * */
	@RequestMapping("/freelist")
	public void list(Model model, @RequestParam(defaultValue = "0") int nowPage) {
		Pageable pageable = PageRequest.of(nowPage, 10, Direction.DESC, "freeBno");
		
		Page<FreeBoard> pageList = freeService.selectAll(pageable);
		
		model.addAttribute("pageList", pageList); //뷰페이지에서 ${pageList.메소드이름}
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
	public ModelAndView update(FreeBoard board) {//내용 비번, 제목, 글번호
		FreeBoard dbBoard = freeService.update(board);
		
		return new ModelAndView("board/read", "board", dbBoard);
	}
	
	/**
	 * 삭제하기 
	 * */
	@RequestMapping("/delete")
	public String delete(Long freeBno) {
		 freeService.delete(freeBno);
		 
		 return "redirect:/board/list";
	}
}



