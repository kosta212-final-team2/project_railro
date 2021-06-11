package kosta.web.mvc.board.dto;

import java.time.LocalDateTime;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.FreeDeclare;
import lombok.Getter;

@Getter
public class FreeDeclareDto {

	private Long freeDno; //신고번호
	private String freeReason; //신고사유
	private String memberId; //신고 누른 사람
	private int freeState; //처리 상태
	private LocalDateTime freeDecDate; //신고일
	private FreeBoard freeBoard;
	
	public FreeDeclareDto(FreeDeclare freeDeclare) {
		freeDno = freeDeclare.getFreeDno();
		freeReason = freeDeclare.getFreeReason();
		memberId = freeDeclare.getMemberId();
		freeState = freeDeclare.getFreeState();
		freeDecDate = freeDeclare.getFreeDecDate();
		freeBoard = freeDeclare.getFreeBoard();
	}
}
