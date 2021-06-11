package kosta.web.mvc.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView adminMemberPage(Model model) {
		List<Member> list = memberService.findAll();
		return new ModelAndView("page/admin/memberAdmin", "list", list);
	}
}
