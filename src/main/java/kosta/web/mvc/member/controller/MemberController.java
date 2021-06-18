package kosta.web.mvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
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
}
