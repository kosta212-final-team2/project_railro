package kosta.web.mvc.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	//회원정보수정시 비밀번호 암호화처리를 위한 객체를 주입받는다
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;
		
	
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
		Member member = memberService.findByMemberId(memberId);
		model.addAttribute("member", member);
		return "page/member/mypage";
	}
	
	/**
	 * 회원정보 수정하기 
	 * */
//	@RequestMapping("/updateMember")
//	public ModelAndView updateMember(HttpServletRequest request, Member vo) {
//		System.out.println("1. Member :: "+vo);
//		
//		//회원정보 수정위해 Spring Security 세션 회원정보를 반환받는다
//		Member pvo=(Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		System.out.println("2. Spring Security 세션 수정 전 회원정보:" + vo);
//		
//		//변경할 비밀번호를 암호화한다 
////		String encodePassword=passwordEncoder.encode(vo.getPwd());
////		vo.setPwd(encodePassword);
//		
//		memberService.update(vo.getMemberId());
//		
//		System.out.println("**********************************************");
//		// 수정한 회원정보로 Spring Security 세션 회원정보를 업데이트한다
//		
//		pvo.setName(vo.getName());
//		pvo.setEmail(vo.getEmail());
//		pvo.setAddr(vo.getAddr());
//		pvo.setPhone(vo.getPhone());
//		pvo.setPwd(vo.getPwd());
//		pvo.setPicture(vo.getPicture());
//		System.out.println("3. Spring Security 세션 수정 후 회원정보:" + pvo);
//				
//		
//		return new ModelAndView("page/member/profile");
//	}
	
	/**
	 * 회원정보 수정
	 */
	/*
	 * @RequestMapping("/updateMemberForm") public ModelAndView
	 * updateMemberForm(String memberId) { Member member =
	 * memberService.findByMemberId(memberId); return new
	 * ModelAndView("page/member/profile", "member",member); }
	 */
	
	/**
	 * 회원정보 수정 완료
	 */
	@RequestMapping("/updateMember")
	public ModelAndView updateMember(Member member , String savePwd) {
		Member updateMember = memberService.update(member , savePwd);
		
		//수정이 완료된후에 로그인할대 저장되어 있늠 Authentication정보를 변경해준다.
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
	 * 회원탈퇴
	 */
	@RequestMapping("/deleteMember")
	public String deleteMember(String memberId) {
		memberService.deletebyMemberId(memberId);
		
		
		return "redirect:/member/loginForm";
	}
	
}
