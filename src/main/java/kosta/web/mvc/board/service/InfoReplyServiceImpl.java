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
	public void delete(Long infoRno) {
		infoReplyRepository.deleteById(infoRno);

	}

}
