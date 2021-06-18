package kosta.web.mvc.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Following;
import kosta.web.mvc.member.repository.FollowingRepository;
@Service
public class FollowingServiceImpl implements FollowingService {

	@Autowired
	private FollowingRepository followingRepository;
	
	@Override
	public void insert(Following following) {
		followingRepository.save(following);
	}

	@Override
	public void deleteByFromIdAndToId(String toId, String fromId) {
		followingRepository.deleteByFromIdAndToId(fromId, toId);
		
	}

	@Override
	public Following findByFromIdAndToId(String fromId, String toId) {
		Following following = followingRepository.findByFromIdAndToId(fromId, toId);
		return following;
	}

}
