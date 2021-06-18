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
	public void insert(FreeBoard freeBoard) {
		freeRepository.save(freeBoard);
	}

	@Override
	public FreeBoard selectBy(Long freeBno, boolean state) {
		//state에 따라 조회수 증가여부...
		if(state) {
			freeRepository.readnumUpdate(freeBno);
		}
		
		return freeRepository.findById(freeBno).orElse(null);
	}
	
	@Override
	public Page<FreeBoard>  freeSubjectSearch(String keyword, Pageable pageable) {
		System.out.println("keyword = " + keyword);
		Page<FreeBoard> freeBoardList = freeRepository.findByFreeSubjectContaining(keyword, pageable);
		//List<FreeBoard> freeBoardList = freeRepository.findByFreeContentContaining(keyword);
		
		System.out.println("freeBoardList : " + freeBoardList);
		return freeBoardList;
	}

	@Override
	public Page<FreeBoard>  freeIdSearch(String keyword , Pageable pageable) {
		Page<FreeBoard> freeBoardList = freeRepository.findByMemberIdContaining(keyword ,pageable);
		return freeBoardList;
	}
	
	/*@Override
	public void insert(FreeBoard freeBoard) {
		freeRepository.save(freeBoard);
		
	}*/

	/*@Override
	public FreeBoard update(FreeBoard freeBoard) {
		FreeBoard dbBoard = freeRepository.findById(freeBoard.getFreeBno()).orElse(null);
		
		if(dbBoard==null || !dbBoard.getMemberId().equals(freeBoard.getMemberId())) {
			throw new RuntimeException("작성자정보가 일치하지 않습니다");
		}
		
		//정보수정
		dbBoard.setFreeContent( freeBoard.getFreeContent().replace("<", "&lt;") );
		dbBoard.setFreeSubject(freeBoard.getFreeSubject());
		
		return dbBoard;
	}*/

	/*@Override
	public void delete(Long freeBno) {
		FreeBoard dbBoard = freeRepository.findById(freeBno).orElse(null);
		
		if(dbBoard==null || !dbBoard.getMemberId().equals(getm())) {
			throw new RuntimeException("작성자정보가 일치하지 않습니다");
		}
		
		freeRepository.deleteById(freeBno);
		
	}*/

	/*@Override
	public FreeBoard selectBy(Long freeBno, boolean state) {
		//state에 따라 조회수 증가여부...
		if(state) {
			freeRepository.readnumUpdate(freeBno);
		}
		
		return freeRepository.findById(freeBno).orElse(null);
	}*/

}
