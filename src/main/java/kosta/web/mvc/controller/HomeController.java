package kosta.web.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.domain.Notice;
import kosta.web.mvc.member.service.NoticeService;


@Controller
public class HomeController {
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping("/")
	public String index() {
		System.out.println("call testpage");
		/*
		 * List<Notice> noticeList = noticeService.findAllByToId(memberId);
		 * System.out.println(memberId); model.addAttribute("noticeList", noticeList);
		 */
		return "page/index"; // resources/templates/page/index.html
	}
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess() {
		return "page/loginSuccess";
	}

	@RequestMapping("/about")
	public String aboutPage() {
		return "page/introduce/about";
	}

}
