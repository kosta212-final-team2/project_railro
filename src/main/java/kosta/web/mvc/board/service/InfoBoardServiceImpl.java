package kosta.web.mvc.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.board.repository.InfoBoardRepository;
import lombok.RequiredArgsConstructor;

@Service // id="infoBoardServiceImpl"
@RequiredArgsConstructor
@Transactional
public class InfoBoardServiceImpl implements InfoBoardService {

	private final InfoBoardRepository infoRepository;
	
	@Override
	public List<InfoBoard> selectAll() {
		return null;
	}

	@Override
	public Page<InfoBoard> selectAll(Pageable pageable) {
		//return infoRepository.findAll(Sort.by(Sort.Direction.DESC, "infoBno"));
		return infoRepository.findAll(pageable);
	}

	@Override
	public void insert(InfoBoard board) {
		infoRepository.save(board);
	}

}
