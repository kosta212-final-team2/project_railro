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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.board.repository.InfoBoardRepository;
import kosta.web.mvc.board.service.InfoBoardService;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.repository.MemberRepository;

@Controller
@RequestMapping("/board/info")
public class InfoBoardController {
	
	@Autowired
	private InfoBoardService infoService;
	
	@Autowired
	private InfoBoardRepository infoRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * 글 목록 조회 : 전체검색 및 조건검색으로 합쳐짐
	 */ /*
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int nowPage) {
		System.out.println("call selectAll");
		//model.addAttribute("infoList", infoService.selectAll(pageable));
		
		Pageable pageable = PageRequest.of((nowPage-1), 10, Direction.DESC, "infoBno");
		
		Page<InfoBoard> pageList = infoService.selectAll(pageable);
		
		int blockCount=5;               
		int temp = (nowPage-1) % blockCount; // 시작 페이지 구하기
		int startPage = nowPage - temp;
		
		model.addAttribute("pageList", pageList); 
		
		model.addAttribute("blockCount", blockCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		
		System.out.println(pageList);
		
		return "page/board/info/list"; 
	} */
	
	/**
	 * 전체검색 및 조건검색
	 */
	@RequestMapping("/list")
	public String freeIdSearch(String keyword, String type, Model model, @RequestParam(defaultValue = "1") int nowPage) {

		Pageable pageable = PageRequest.of((nowPage - 1), 10, Direction.DESC, "infoBno");

		Page<InfoBoard> infoSearchList = null;

		if (keyword == null) {
			infoSearchList = infoService.selectAll(pageable);
		}

		else if (type.equals("subject")) {
			infoSearchList = infoService.infoSubjectSearch(keyword, pageable);
		} else if (type.equals("writer")) {
			infoSearchList = infoService.infoIdSearch(keyword, pageable);
		}

		System.out.println("freeSearchList.size() = " + infoSearchList.getContent().size());

		int blockCount=5;               
		int temp = (nowPage-1) % blockCount; // 시작 페이지 구하기
		int startPage = nowPage - temp;
		
		model.addAttribute("infoSearchList", infoSearchList);
		model.addAttribute("blockCount", blockCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("infoList", infoSearchList.getContent());

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
		
		return "redirect:/board/info/list";
	}
	
	/**
	 * 상세보기
	 */
	@RequestMapping("/read/{infoBno}")
	public ModelAndView read(@PathVariable Long infoBno, String flag) {
		boolean state = flag==null ? true : false;
		
		InfoBoard infoBoard = infoService.selectBy(infoBno, state);//state가 true이면 조회수증가, false 조회수 증가안함.
		Member member = memberRepository.findByMemberId(infoBoard.getMemberId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("member", member);
		mv.setViewName("page/board/info/read");
		mv.addObject("board", infoBoard);
		
		return mv;
	}
	
	/**
	 * 글 수정하기 폼
	 */
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(Long infoBno) {
		InfoBoard infoBoard = infoService.selectBy(infoBno, false);
		return new ModelAndView("page/board/info/update", "board", infoBoard);
	}
	
	/**
	 * 글 수정 후 저장
	 */
	@RequestMapping("/update")
	public ModelAndView update(InfoBoard infoBoard) {
		InfoBoard dbBoard = infoService.update(infoBoard);
		return new ModelAndView("page/board/info/read", "board", dbBoard);
	}
	
	/**
	 * 글 삭제하기
	 */
	@RequestMapping("/delete")
	public String delete(Long infoBno) {
		infoService.delete(infoBno);
		return "redirect:/board/info/list";
	}
}
