package kosta.web.mvc.member.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Following;
import kosta.web.mvc.member.repository.FollowingRepository;
@Service
@Transactional
public class FollowingServiceImpl implements FollowingService {

	@Autowired
	private FollowingRepository followingRepository;
	
	@Override
	public void insert(Following following) {
		followingRepository.save(following);
	}

	@Override
	public void deleteByFromIdAndToId(String toId, String fromId) {
		System.out.println(toId);
		System.out.println(fromId);
		followingRepository.deleteByFromIdAndToId(toId, fromId);
		
	}

	@Override
	public Following findByFromIdAndToId(String fromId, String toId) {
		Following following = followingRepository.findByFromIdAndToId(fromId, toId);
		return following;
	}

}
