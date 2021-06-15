package kosta.web.mvc.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.member.domain.Authorities;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/")
	public String adminHomePage() {
		return "page/admin/home";
	}
	
	@RequestMapping("/member")
	public ModelAndView adminMemberPage() {
		List<Member> list = memberService.findAll();
		return new ModelAndView("page/admin/memberAdmin", "list", list);
	}
	
	@RequestMapping("/read")
	public String readMemberPage(String memberId, Model model) {
		Member member = memberService.findByMemberId(memberId);
		List<Authorities> list = memberService.findAllByMemberId(memberId);
		model.addAttribute("member", member);
		model.addAttribute("auth", list);
		
		return "page/admin/read";
	}
}
