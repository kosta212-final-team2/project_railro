package kosta.web.mvc.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kosta.web.mvc.board.domain.TripBoard;
import kosta.web.mvc.board.repository.TripBoardRepository;
import lombok.RequiredArgsConstructor;

@Service // id="tripBoardServiceImpl"
@RequiredArgsConstructor
@Transactional
public class TripBoardServiceImpl implements TripBoardService {

	private final TripBoardRepository tripRepository;
	
	@Override
	public List<TripBoard> selectAll() {
		return null;
	}

	@Override
	public Page<TripBoard> selectAll(Pageable pageable) {
		//return TripRepository.findAll(Sort.by(Sort.Direction.DESC, "TripBno"));
		return tripRepository.findAll(pageable);
	}
	
	@Override
	public Page<TripBoard> tripSubjectSearch(String keyword, Pageable pageable) {
		System.out.println("keyword = " + keyword);
		Page<TripBoard> tripBoardList = tripRepository.findByTripSubjectContaining(keyword, pageable);
		//List<FreeBoard> freeBoardList = freeRepository.findByFreeContentContaining(keyword);
		
		System.out.println("TripBoardList : " + tripBoardList);
		return tripBoardList;
	}

	@Override
	public Page<TripBoard> tripIdSearch(String keyword, Pageable pageable) {
		Page<TripBoard> tripBoardList = tripRepository.findByMemberIdContaining(keyword ,pageable);
		return tripBoardList;
	}

	@Override
	public void insert(TripBoard board) {
		tripRepository.save(board);
	}

	@Override
	public TripBoard selectBy(Long tripBno, boolean state) {
		//state에 따라 조회수를 증가시킨다
		if(state) {
			tripRepository.readnumUpdate(tripBno);
		}
		return tripRepository.findById(tripBno).orElse(null);
	}

	@Override
	public TripBoard update(TripBoard tripBoard) {
		TripBoard dbBoard = tripRepository.findById(tripBoard.getTripBno()).orElse(null);
		
		if(dbBoard==null) {
			throw new RuntimeException("글이 수정되지 않았습니다");
		}
		
		// 정보 수정
		dbBoard.setTripContent(tripBoard.getTripContent().replace("<", "&lt;"));
		dbBoard.setTripSubject(tripBoard.getTripSubject());
		
		return dbBoard;
	}

	@Override
	public void delete(Long tripBno) {
		TripBoard dbBoard = tripRepository.findById(tripBno).orElse(null);
		
		if(dbBoard==null) {
			throw new RuntimeException("글이 삭제되지 않았습니다");
		}
		
		tripRepository.deleteById(tripBno);
	}



}
