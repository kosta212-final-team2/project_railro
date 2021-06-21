package kosta.web.mvc.member.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.member.domain.Notice;
import kosta.web.mvc.member.repository.NoticeRepository;
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeRepository noticeRepository;
	
	@Override
	public List<Notice> findAllByToId(String toId) {
		
		return noticeRepository.sellectByToID(toId);
	}

	@Override
	public void insertFollowingNotice(Notice notice) {
		noticeRepository.save(notice);
		
	}

	@Override
	public void insertUnfollowingNotice(Notice notice) {
		noticeRepository.save(notice);
	}

}
