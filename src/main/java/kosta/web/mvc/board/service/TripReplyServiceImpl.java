package kosta.web.mvc.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.TripReply;
import kosta.web.mvc.board.repository.TripReplyRepository;

@Service
@Transactional
public class TripReplyServiceImpl implements TripReplyService {
	
	@Autowired
	private TripReplyRepository tripReplyRepository;

	@Override
	public void insert(TripReply tripReply) {
		tripReplyRepository.save(tripReply);
	}

	@Override
	public void delete(Long tripRno) {
		tripReplyRepository.deleteById(tripRno);

	}

}
