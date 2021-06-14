package kosta.web.mvc.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.FreeReply;
import lombok.Getter;
import lombok.Setter;

public class FreeBoardDto {
	
	private Long freeBno; // 글번호
	private String freeSubject;// 글제목
	private String memberId;// 작성자
	private String freeContent;// 내용
	private int freeReadnum; // 조회수
	private int freeVote; // 추천수
	private LocalDateTime freeRegdate; // 등록일
	private List<FreeReply> freeReplyList; //댓글목록
	
	public FreeBoardDto(FreeBoard freeBoard) {
		freeBno = freeBoard.getFreeBno();
		freeSubject = freeBoard.getFreeSubject();
		memberId = freeBoard.getMemberId();
		freeContent = freeBoard.getFreeContent();
		freeReadnum = freeBoard.getFreeReadnum();
		freeVote = freeBoard.getFreeVote();
		freeRegdate = freeBoard.getFreeRegdate();
		freeReplyList = freeBoard.getFreeReplyList(); //map(OrderLineItemDto::new).collect(Collectors.toList());	
	}
}
