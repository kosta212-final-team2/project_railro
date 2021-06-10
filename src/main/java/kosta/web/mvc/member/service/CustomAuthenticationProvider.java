package kosta.web.mvc.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Authorities;
import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.repository.AuthoritiesRepository;
import kosta.web.mvc.member.repository.MemberRepository;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String memberId = authentication.getName();
		
		Member member = memberRepository.findByMemberId(memberId);
		
		if(member==null) {
			throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
		}
		
		String pwd = (String)authentication.getCredentials();
		
		if(!passwordEncoder.matches(pwd, member.getPwd())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}
		
		List<Authorities> list = authoritiesRepository.findAllByMemberId(memberId);
		
		if(list.isEmpty()){
			throw new UsernameNotFoundException("해당 아이디는 아무 권한이 없습니다.");
		}
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		for(Authorities authorities : list) {
			authList.add(new SimpleGrantedAuthority(authorities.getRole()));
		}
		return new UsernamePasswordAuthenticationToken(member, null, authList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
