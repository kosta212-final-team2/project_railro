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
	

	@RequestMapping("/follow")
	public void following(String toId, String fromId) {
		
		System.out.println("following 호출");
		
		
		Following following = new Following(null, fromId, toId, null);
		followingService.insert(following);

			
	}
	
	@RequestMapping("/unfollow")
	public void unfollowing(String toId, String fromId) {
		
		System.out.println("unfollowing 호출");
		
		Following following = new Following(null, fromId, toId, null);
			
		followingService.deleteByFromIdAndToId(fromId, toId);
			
	}
}
