package kosta.web.mvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.web.mvc.member.domain.Following;
import kosta.web.mvc.member.domain.Notice;
import kosta.web.mvc.member.service.FollowingService;
import kosta.web.mvc.member.service.NoticeService;

@RestController
public class FollowingController {

	@Autowired
	private FollowingService followingService;
	
	@Autowired
	private NoticeService noticeService;
	

	@RequestMapping("/follow")
	public void following(String toId, String fromId) {
		
		System.out.println("following 호출");
		
		
		Following following = new Following(null, fromId, toId, null);
		followingService.insert(following);
		
		String message = "님이 팔로잉하였습니다.";
		noticeService.insertFollowingNotice(new Notice(null, message, fromId, toId, 0, null));
		
			
	}
	
	@RequestMapping("/unfollow")
	public void unfollowing(String toId, String fromId) {
		
		System.out.println("unfollowing222222 호출");
		System.out.println(toId);
		System.out.println(fromId);
		followingService.deleteByFromIdAndToId(fromId, toId);
		
		String message = "님이 언팔로잉하였습니다.";
		noticeService.insertUnfollowingNotice(new Notice(null, message, fromId, toId, 0, null));
	}
}
