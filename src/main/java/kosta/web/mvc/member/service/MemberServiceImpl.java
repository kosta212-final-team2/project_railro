package kosta.web.mvc.member.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Authorities;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.repository.AuthoritiesRepository;
import kosta.web.mvc.member.repository.MemberRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insert(Member member) {
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		Authorities authorities = new Authorities();
		authorities.setMember(member);
		authorities.setRole("ROLE_ADMIN");
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
	

}
