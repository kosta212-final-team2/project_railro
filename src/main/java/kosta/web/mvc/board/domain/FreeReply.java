package kosta.web.mvc.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FreeReply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "reply_rno_seq")
	@SequenceGenerator(sequenceName = "reply_rno_seq" , allocationSize = 1 , name = "reply_rno_seq")
	private Long freeRno; // 댓글번호
	private String freeReplyContent; //댓글내용
	private String memberId; //댓글 작성자
	
	@CreationTimestamp
	private LocalDateTime freeReplyRegdate;
	
	@ManyToOne
	@JoinColumn(name = "free_bno")
	private FreeBoard freeBoard;
	
	
}
