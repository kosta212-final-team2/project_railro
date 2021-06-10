package kosta.web.mvc.member.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.repository.MemberRepository;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insert(Member member) {
		member.setPwd(passwordEncoder.encode(member.getPwd()));
		
		memberRepository.save(member);
	}

}
