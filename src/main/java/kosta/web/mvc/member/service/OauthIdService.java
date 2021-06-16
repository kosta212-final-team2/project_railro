package kosta.web.mvc.member.service;

import java.util.List;
import java.util.Map;

import kosta.web.mvc.member.domain.OauthId;

public interface OauthIdService {
	OauthId getOAuthInfoByUniqueId(String uniqueId);
}
