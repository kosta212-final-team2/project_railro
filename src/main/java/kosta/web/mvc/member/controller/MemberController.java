package kosta.web.mvc.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.map.dto.TravelPlan;
import kosta.web.mvc.map.service.PlanService;
import kosta.web.mvc.member.domain.Following;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.domain.Notice;
import kosta.web.mvc.member.service.FollowingService;
import kosta.web.mvc.member.service.MemberService;
import kosta.web.mvc.member.service.NoticeService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	//회원정보수정시 비밀번호 암호화처리를 위한 객체를 주입받는다
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private FollowingService followingService;
	
	@Autowired
	private PlanService planService;
	
	@RequestMapping("/loginForm")
	public String loginFormPage() {
		return "page/member/login";
	}
	
	@RequestMapping("/registerForm")
	public String registerFormPage() {
		return "page/member/register";
	}
	
	@RequestMapping("/register")
	public String register(Member member) {
		memberService.insert(member);
		
		return "page/member/login";
	}
	
	@RequestMapping("/mypage")
	public String profilePage(String memberId, Model model) {
		Member loginMember = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String fromId = loginMember.getMemberId();
		if(memberId==null) {
			memberId=fromId;
		}
		Member member = memberService.findByMemberId(memberId);
		
		Following following = followingService.findByFromIdAndToId(fromId, memberId);
		//나의 게시물 리스트 출력
		List<InfoBoard> list = memberService.selectINfoBoardByMember(memberId);
		List<TravelPlan> planList = planService.getTravelPlanByUser(memberId);
		model.addAttribute("planList", planList);
		model.addAttribute("list", list);
		model.addAttribute("fromId", fromId);
		model.addAttribute("following", following);
		model.addAttribute("member", member);
		return "page/member/mypage";
	}
	
	
	/**
	 * 회원정보 수정 완료
	 */
	@RequestMapping("/updateMember")
	public ModelAndView updateMember(Member member , String savePwd) {
		Member updateMember = memberService.update(member , savePwd);
		
		//수정이 완료된후에 로그인할때 저장되어 있음 Authentication정보를 변경해준다.
		Member authMember = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		authMember.setName(updateMember.getName());
		authMember.setEmail(updateMember.getEmail());
		authMember.setAddr(updateMember.getAddr());
		authMember.setPhone(updateMember.getPhone());
		authMember.setPwd(updateMember.getPwd());
		authMember.setPicture(updateMember.getPicture());
		
		
		return new ModelAndView("page/member/mypage","member", updateMember);
	}
	/**
	 * 프로필 사진 변경
	 */
	@RequestMapping("/updatePicture")
	public ModelAndView updatePictrure(Member member, String memberPicture) {
		
		Member updateMember = memberService.update(member, memberPicture);
		
		updateMember.setPicture(memberPicture);
		return new ModelAndView("page/member/mypage","member",updateMember);
	}
	
	/**
	 * 
	 */
	
	/**
	 * 회원탈퇴
	 */
	@RequestMapping("/deleteMember")
	public String deleteMember(String memberId) {
		memberService.deletebyMemberId(memberId);
		memberService.updateAuthorities(memberId);
	
		
		return "redirect:/member/loginForm";
	}
	
	/**
	 * 알림
	 * */
	@RequestMapping("/notice")
	public String noticePage(String memberId, Model model) {
		List<Notice> list = noticeService.findAllByToId(memberId);
		model.addAttribute("list", list);
		return "page/member/notice";
	}
	
}
