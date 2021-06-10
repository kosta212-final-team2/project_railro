package kosta.web.mvc.member.service;

import kosta.web.mvc.member.domain.Member;

public interface MemberService {
	/**
	 * 회원 등록
	 * */
	void insert(Member member);
	
	/**
	 * 로그인
	 * */
	Member login(Member member);
}
