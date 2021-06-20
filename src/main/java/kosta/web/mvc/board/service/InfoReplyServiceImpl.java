package kosta.web.mvc.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.InfoReply;
import kosta.web.mvc.board.repository.InfoReplyRepository;

@Service
@Transactional
public class InfoReplyServiceImpl implements InfoReplyService {
	
	@Autowired
	private InfoReplyRepository infoReplyRepository;

	@Override
	public void insert(InfoReply infoReply) {
		infoReplyRepository.save(infoReply);
	}


	@Override
	public InfoReply selectBy(Long infoRno) {
		return infoReplyRepository.findById(infoRno).orElse(null);
	}

	@Override
	public InfoReply update(InfoReply infoReply) {
		infoReplyRepository.deleteTest(infoReply.getInfoRno());
		
		infoReplyRepository.save(infoReply);
		
		return infoReply;
	}

	@Override
	public void delete(Long infoRno) {
//		InfoReply dbReply = infoReplyRepository.findById(infoRno).orElse(null);
//		
//		if(dbReply==null) {
//			throw new RuntimeException("댓글이 삭제되지 않았습니다");
//		}
		
		infoReplyRepository.deleteTest(infoRno);
	}
}
