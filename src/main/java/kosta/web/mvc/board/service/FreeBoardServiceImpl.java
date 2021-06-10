package kosta.web.mvc.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService {

	private final FreeBoardRepository freeRepository;
	
	@Override
	public List<FreeBoard> selectAll() {
		
		return null;
	}

	@Override
	public Page<FreeBoard> selectAll(Pageable pageable) {
		
		return freeRepository.findAll(pageable);
	}

	@Override
	public void insert(FreeBoard board) {
		freeRepository.save(board);

	}

	@Override
	public FreeBoard selectBy(Long freeBno, boolean state) {
		// 조회수 증가 여부
		if(state) {
			freeRepository.readnumUpdate(freeBno);
		}
		
		return freeRepository.findById(freeBno).orElse(null);
	}

	@Override
	public FreeBoard update(FreeBoard board) {
		FreeBoard dbBoard = freeRepository.findById(board.getFreeBno()).orElse(null);
		
		if(dbBoard == null || !dbBoard.getMemberId().equals(board.getMemberId())) {
			throw new RuntimeException("오류로 수정 할 수 없습니다");
		}
		
		//수정
		dbBoard.setFreeContent(board.getFreeContent().replace("<", "&lt;"));
		dbBoard.setFreeSubject(board.getFreeSubject());
		
		return dbBoard;
	}

	@Override
	public void delete(Long freeBno) {
		FreeBoard dbBoard = freeRepository.findById(freeBno).orElse(null);
		
		/*
		 * if(dbBoard == null || !dbBoard.getMemberId().equals() { throw new
		 * RuntimeException("오류로 삭제 할 수 없습니다"); }
		 */
		
		freeRepository.deleteById(freeBno);

	}

}
