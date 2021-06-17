package kosta.web.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.dto.FreeBoardDto;
import kosta.web.mvc.board.repository.FreeBoardRepository;
import kosta.web.mvc.board.service.FreeBoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class FreeBoardController {

	@Autowired
	private final FreeBoardService freeService;
	
	@Autowired
	private FreeBoardRepository freeRepository;
	
	/**
	 * 글 등록폼
	 * */
	/*@RequestMapping("/write")
	public void write() {}*/
	
	/**
	 * 글 등록하기
	 * */
	/*@RequestMapping("/insert")
	public String insert(FreeBoard freeBoard) {
		
		//등록전에 입력한데이터에 유효하지 않는 특수문자,태그 등이 있는지 체크!! - filter 
		String freeContent = freeBoard.getFreeContent().replace("<", "&lt;");
		freeBoard.setFreeContent(freeContent);
		
		freeService.insert(freeBoard);
		
		return "redirect:/free/list";
	}*/
	
	/**
	 * 수정 완료
	 * */
	/*@RequestMapping("/update")
	public ModelAndView update(FreeBoard freeBoard) {//내용 비번, 제목, 글번호
		FreeBoard dbBoard = freeService.update(freeBoard);
		
		return new ModelAndView("free/read", "freeBoard", dbBoard);
	}*/
	
	/**
	 * 글 목록 조회
	 */
	/*@RequestMapping("/free")
	public String list(@PageableDefault Pageable pageable, Model model) {
		System.out.println("call selectAll");
    model.addAttribute("freeList", freeService.selectAll(pageable));
		return "page/board/free/list"; 
	}*/
	
	/**
	 * 검색(제목,작성자)
	 * */
	/*@RequestMapping("/free/subjectSearch")
    public String freeSubjectSearch(String keyword, Model model) {

        List<FreeBoard> freeSearchList = freeService.freeSubjectSearch(keyword);

        model.addAttribute("freeSearchList", freeSearchList);

        return "page/board/free/searchPage";
    }*/
	
	/*@RequestMapping("/free/idSearch")
    public String freeIdSearch(String keyword, Model model) {

        List<FreeBoard> freeSearchList = freeService.freeIdSearch(keyword);

        model.addAttribute("freeSearchList", freeSearchList);

        return "page/board/free/searchPage";
    }*/
		
	/*@RequestMapping("/free/search")
    public String freeIdSearch(String keyword, String type, Model model, RedirectAttributes redirectAttributes) {
		
		List<FreeBoard> freeSearchList = null;
		
		if(type.equals("subject")) {
			freeSearchList = freeService.freeSubjectSearch(keyword);
		}
		if(type.equals("writer")) {
			freeSearchList = freeService.freeIdSearch(keyword);
		}
		
		System.out.println("freeSearchList.size() = "+freeSearchList.size());
		redirectAttributes.addAttribute("type", type);
		redirectAttributes.addAttribute("keyword", keyword);
		
        model.addAttribute("freeSearchList", freeSearchList);

        return "page/board/free/list";
    }*/
	
	/**
	 * 상세보기
	 * */
	/*@RequestMapping("/read/{freeBno}")
	public ModelAndView read(@PathVariable Long freeBno, String flag) {
		boolean state = flag==null ? true : false;
		
		FreeBoard freeBoard = freeService.selectBy(freeBno, state);//state가 true이면 조회수증가, false 조회수 증가안함.
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("free/read");
		mv.addObject("board", freeBoard);
		
		return mv;
	}*/
	
	/**
	 * 삭제하기 
	 * */
	/*@RequestMapping("/delete")
	public String delete(Long bno) {
		 freeService.delete(bno);
		 
		 return "redirect:/free/list";
	}*/
	

	
	////////////////////////
	/**
	 *  전체검색 또는 조건 검색
	 * */
	@RequestMapping("/free")
    public String freeIdSearch(String keyword, String type, Model model , @RequestParam(defaultValue = "1") int nowPage) {
		
		Pageable pageable = PageRequest.of(nowPage-1, 10);
		 
		Page<FreeBoard> freeSearchList = null;
		
		if(keyword==null ) {
			freeSearchList =  freeService.selectAll(pageable);
		}
		
		else if(type.equals("subject")) {
			freeSearchList = freeService.freeSubjectSearch(keyword,pageable);
		}
		else if(type.equals("writer")) {
			freeSearchList = freeService.freeIdSearch(keyword, pageable);
		}
		
		System.out.println("freeSearchList.size() = "+freeSearchList.getContent().size());
	
        model.addAttribute("freeSearchList", freeSearchList);
        model.addAttribute("freeList", freeSearchList.getContent());
        
        return "page/board/free/list";
    }
}



