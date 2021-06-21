package kosta.web.mvc.member.service;

import java.util.List;

import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.member.domain.Authorities;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.domain.OauthId;

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
	
	/**
	 * 회원정보 수정
	 */
	Member update(Member member , String savePwd);
	
	void naverMemberInsert(Member member);
	
	void naverOauthInsert(OauthId oauthId);
	List<Authorities> findAllByMemberId(String memberId);
	
	public void updateAuthorities(String memberId);
	/**
	 * id로 해당회원 삭제
	 * */
	void deletebyMemberId(String memberId);
	
	/**
	 * 
	 */
	List<InfoBoard> selectINfoBoardByMember(String memberId);
	
	/**
	 * 프로필 사진 변경
	 * @return 
	 */
	public void imgUpdate(String memberId, String picture);
	
	public void savePicture(Member member);
}
