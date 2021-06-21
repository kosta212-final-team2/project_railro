package kosta.web.mvc.member.service;

import java.util.List;

import kosta.web.mvc.member.domain.Notice;

public interface NoticeService {
	
	List<Notice> findAllByToId(String toId);
	
	void insertFollowingNotice(Notice notice);
	
	void insertUnfollowingNotice(Notice notice);
}
