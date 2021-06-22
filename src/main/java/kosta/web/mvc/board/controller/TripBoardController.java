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
import kosta.web.mvc.board.domain.TripBoard;
import kosta.web.mvc.board.repository.TripBoardRepository;
import kosta.web.mvc.board.service.TripBoardService;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.repository.MemberRepository;

@Controller
@RequestMapping("/board/trip")
public class TripBoardController {
	
	@Autowired
	private TripBoardService tripService;
	
	@Autowired
	private TripBoardRepository tripRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	/**
	 * 글 목록 조회 : 전체검색 및 조건검색으로 합쳐짐
	 */ /*
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int nowPage) {
		System.out.println("call selectAll");
		//model.addAttribute("tripList", tripService.selectAll(pageable));
		
		Pageable pageable = PageRequest.of((nowPage-1), 10, Direction.DESC, "tripBno");
		
		Page<tripBoard> pageList = tripService.selectAll(pageable);
		
		int blockCount=5;               
		int temp = (nowPage-1) % blockCount; // 시작 페이지 구하기
		int startPage = nowPage - temp;
		
		model.addAttribute("pageList", pageList); 
		
		model.addAttribute("blockCount", blockCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		
		System.out.println(pageList);
		
		return "page/board/trip/list"; 
	} */
	
	/**
	 * 전체검색 및 조건검색
	 */
	@RequestMapping("/list")
	public String freeIdSearch(String keyword, String type, Model model, @RequestParam(defaultValue = "1") int nowPage) {

		Pageable pageable = PageRequest.of((nowPage - 1), 10, Direction.DESC, "tripBno");

		Page<TripBoard> tripSearchList = null;

		if (keyword == null) {
			tripSearchList = tripService.selectAll(pageable);
		}

		else if (type.equals("subject")) {
			tripSearchList = tripService.tripSubjectSearch(keyword, pageable);
		} else if (type.equals("writer")) {
			tripSearchList = tripService.tripIdSearch(keyword, pageable);
		}

		System.out.println("freeSearchList.size() = " + tripSearchList.getContent().size());

		int blockCount=5;               
		int temp = (nowPage-1) % blockCount; // 시작 페이지 구하기
		int startPage = nowPage - temp;
		
		model.addAttribute("tripSearchList", tripSearchList);
		model.addAttribute("blockCount", blockCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("tripList", tripSearchList.getContent());

		return "page/board/trip/list";
	}
	
	/**
	 * 글 등록폼
	 * */
	@RequestMapping("/write")
	public String write() {
		return "page/board/trip/write";
	}
	
	/**
	 * 글 등록하기
	 * */
	@RequestMapping("/insert")
	public String insert(TripBoard board) {
		
		//등록전에 입력한데이터에 유효하지 않는 특수문자,태그 등이 있는지 체크!! - filter 
		String content = board.getTripContent().replace("<", "&lt;");
		board.setTripContent(content);
		
		tripService.insert(board);
		
		return "redirect:/board/trip/list";
	}
	
	/**
	 * 상세보기
	 */
	@RequestMapping("/read/{tripBno}")
	public ModelAndView read(@PathVariable Long tripBno, String flag) {
		boolean state = flag==null ? true : false;
		
		TripBoard tripBoard = tripService.selectBy(tripBno, state);//state가 true이면 조회수증가, false 조회수 증가안함.
		Member member = memberRepository.findByMemberId(tripBoard.getMemberId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("member", member);
		mv.setViewName("page/board/trip/read");
		mv.addObject("board", tripBoard);
		
		return mv;
	}
	
	/**
	 * 글 수정하기 폼
	 */
	@RequestMapping("/updateForm")
	public ModelAndView updateForm(Long tripBno) {
		TripBoard tripBoard = tripService.selectBy(tripBno, false);
		return new ModelAndView("page/board/trip/update", "board", tripBoard);
	}
	
	/**
	 * 글 수정 후 저장
	 */
	@RequestMapping("/update")
	public ModelAndView update(TripBoard tripBoard) {
		TripBoard dbBoard = tripService.update(tripBoard);
		Member member = memberRepository.findByMemberId(tripBoard.getMemberId());
		ModelAndView mv = new ModelAndView("page/board/trip/read", "board", dbBoard);
		mv.addObject("tripBno", tripBoard.getTripBno());
		mv.addObject("member", member);
		return mv;
	}
	
	/**
	 * 글 삭제하기
	 */
	@RequestMapping("/delete")
	public String delete(Long tripBno) {
		tripService.delete(tripBno);
		return "redirect:/board/trip/list";
	}
}
