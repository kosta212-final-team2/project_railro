package kosta.web.mvc.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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

}
