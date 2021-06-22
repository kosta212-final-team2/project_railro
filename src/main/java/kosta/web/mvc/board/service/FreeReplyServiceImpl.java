package kosta.web.mvc.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.FreeReply;
import kosta.web.mvc.board.repository.FreeReplyRepository;

@Service
@Transactional
public class FreeReplyServiceImpl implements FreeReplyService {

	@Autowired
	private FreeReplyRepository freeReplyRepository;
	
	@Override
	public void insert(FreeReply freeReply) {
		freeReplyRepository.save(freeReply);
	}

	@Override
	public void delete(Long freeRno) {//id는 댓글 글번호
		freeReplyRepository.deleteTest(freeRno);
	}

}
