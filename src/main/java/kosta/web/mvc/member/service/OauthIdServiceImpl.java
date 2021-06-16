package kosta.web.mvc.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.domain.OauthId;
import kosta.web.mvc.member.repository.OauthRepository;
@Service
public class OauthIdServiceImpl implements OauthIdService {

	private OauthRepository oauthRepository;
	@Override
	public OauthId getOAuthInfoByUniqueId(String uniqueId) {
		OauthId oauthId = oauthRepository.login(uniqueId);
		Map<OauthId, String> aRow = new HashMap<>();
		
		return null;
	}
	
	@Override
	public Member findMemberByNaverId(String naverId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void registerMember(String naverId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public OauthId findOauthIdByNaverId(String naverId) {
		return oauthRepository.login(naverId);
	}
	@Override
	public void insertOauthId(OauthId oauthId) {
		// TODO Auto-generated method stub
		
	}

}
