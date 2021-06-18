package kosta.web.mvc.board.dto;

import java.time.LocalDateTime;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.FreeReply;
import lombok.Getter;

@Getter
public class FreeReplyDto {

	private Long freeRno; // 댓글번호
	private String freeReplyContent; //댓글내용
	private String memberId; //댓글 작성자
	private LocalDateTime freeReplyRegdate;
	private FreeBoard freeBoard;
	
	public FreeReplyDto(FreeReply freeReply) {
		freeRno = freeReply.getFreeRno();
		freeReplyContent = freeReply.getFreeReplyContent();
		memberId = freeReply.getMember().getMemberId();
		freeReplyRegdate = freeReply.getFreeReplyRegdate();
		freeBoard = freeReply.getFreeBoard();
	}
}
