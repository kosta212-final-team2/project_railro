package kosta.web.mvc.member.service;

import kosta.web.mvc.member.domain.Following;

public interface FollowingService {
	
	void insert(Following following);
	
	void deleteByFromIdAndToId(String fromId, String toId);
}
