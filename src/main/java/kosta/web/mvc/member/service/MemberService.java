package kosta.web.mvc.member.service;

import java.util.List;

import kosta.web.mvc.member.domain.Authorities;
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
	
	/**
	 * 전체회원조회
	 * */
	List<Member> findAll();
	
	/**
	 * id로 해당회원 조회
	 * */
	Member findByMemberId(String memberId);
	
	/**
	 * id로 해당회원의 권한 조회
	 * */
	List<Authorities> findAllByMemberId(String memberId);
}
