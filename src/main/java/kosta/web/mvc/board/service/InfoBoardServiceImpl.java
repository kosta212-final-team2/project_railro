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

	@Override
	public InfoBoard selectBy(Long infoBno, boolean state) {
		//state에 따라 조회수를 증가시킨다
		if(state) {
			infoRepository.readnumUpdate(infoBno);
		}
		return infoRepository.findById(infoBno).orElse(null);
	}

	@Override
	public InfoBoard update(InfoBoard infoBoard) {
		InfoBoard dbBoard = infoRepository.findById(infoBoard.getInfoBno()).orElse(null);
		
		if(dbBoard==null) {
			throw new RuntimeException("글이 수정되지 않았습니다");
		}
		
		// 정보 수정
		dbBoard.setInfoContent(infoBoard.getInfoContent().replace("<", "&lt;"));
		dbBoard.setInfoSubject(infoBoard.getInfoSubject());
		
		return dbBoard;
	}

	@Override
	public void delete(Long infoBno) {
		InfoBoard dbBoard = infoRepository.findById(infoBno).orElse(null);
		
		if(dbBoard==null) {
			throw new RuntimeException("글이 삭제되지 않았습니다");
		}
		
		infoRepository.deleteById(infoBno);
	}

}
