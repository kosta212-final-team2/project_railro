package kosta.web.mvc.board.dto;

import java.time.LocalDateTime;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.FreeRecommend;
import lombok.Getter;

@Getter
public class FreeRecommendDto {

	private Long freeVno;
	private String memberId;
	private LocalDateTime freeVoteDate;
	private FreeBoard freeBoard;
	
	public FreeRecommendDto(FreeRecommend freeRecommend) {
		freeVno = freeRecommend.getFreeVno();
		memberId = freeRecommend.getMemberId();
		freeVoteDate = freeRecommend.getFreeVoteDate();
		freeBoard = freeRecommend.getFreeBoard();
	}
}
