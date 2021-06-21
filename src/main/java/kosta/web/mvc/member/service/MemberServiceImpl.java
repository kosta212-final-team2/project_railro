  
package kosta.web.mvc.member.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.board.repository.InfoBoardRepository;
import kosta.web.mvc.member.domain.Authorities;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.domain.OauthId;
import kosta.web.mvc.member.repository.AuthoritiesRepository;
import kosta.web.mvc.member.repository.MemberRepository;
import kosta.web.mvc.member.repository.OauthRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	OauthRepository oauthRepository;
	
	@Autowired
	MemberRepository memberRepository;
	 
	@Autowired
	AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	InfoBoardRepository infoBoardRepository;
	
	@Override
	public void insert(Member member) {
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		Authorities authorities = new Authorities();
		authorities.setMember(member);
		authorities.setRole("ROLE_MEMBER");
		authoritiesRepository.save(authorities);
		
		memberRepository.save(member);
	}

	@Override
	public Member login(Member member) {
		return memberRepository.login(member);
	}

	@Override
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public Member findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}

	@Override
	public Member update(Member member, String savePwd) {
		 //비밀번호가 맞느지 체크!!!
		Member dbM = memberRepository.findByMemberId(member.getMemberId());
		
		if(dbM ==null) {
		   throw new RuntimeException("존재하지 않는 id이므로 다시 로그인하고 이용해주세요 수정실패...");
		}
		
		if(!passwordEncoder.matches(savePwd, dbM.getPwd())) {
			throw new RuntimeException("비번 오류... 수정실패...");
		}
		
		//수정전에 비밀번호 암호화 해서 넣는다.
		dbM.setPwd(passwordEncoder.encode(member.getPwd()));
		
		dbM.setName(member.getName());
		dbM.setEmail(member.getEmail());
		dbM.setAddr(member.getAddr());
		dbM.setPhone(member.getPhone());
		dbM.setPicture(member.getPicture());
		
		
		return dbM;
	}

	@Override
	public void naverMemberInsert(Member member) {
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		Authorities authorities = new Authorities();
		authorities.setMember(member);
		authorities.setRole("ROLE_MEMBER");
		authoritiesRepository.save(authorities);
		memberRepository.save(member);
		
	}

	@Override
	public void naverOauthInsert(OauthId oauthId) {
		oauthRepository.save(oauthId);
		
	}
	
	
	
	public List<Authorities> findAllByMemberId(String memberId) {
		return authoritiesRepository.findAllByMemberId(memberId);
	}

	@Override
	public void deletebyMemberId(String memberId) {
		oauthRepository.deleteByMemberId(memberId);
	}
	
	@Override
	public void updateAuthorities(String memberId) {
		authoritiesRepository.updateAuthorities(memberId);
	}

	@Override
	public List<InfoBoard> selectINfoBoardByMember(String memberId) {
		
		return infoBoardRepository.selectINfoBoardByMember(memberId);
	}

	@Override
	public void imgUpdate(String memberId, String picture) {
		Member member = findByMemberId(memberId);
		member.setPicture(picture);
		
		savePicture(member);
		
	}

	@Override
	public void savePicture(Member member) {
		memberRepository.save(member);
		
	}

	


	

}