package kosta.web.mvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.web.mvc.member.domain.Following;
import kosta.web.mvc.member.service.FollowingService;

@RestController
public class FollowingController {

	@Autowired
	private FollowingService followingService;
	
	private boolean state = true;
	@RequestMapping("/follow")
	public boolean following(String toId, String fromId) {
		
		System.out.println("following 호출");
		
		if(state==true) {
			Following following = new Following(null, fromId, toId, null);
			followingService.insert(following);
			state = false;
			System.out.println(state);
			
			return true;
		}
			
		followingService.deleteByFromIdAndToId(fromId, toId);
		state = true;
		System.out.println(state);
			
		return false;
	}
}
