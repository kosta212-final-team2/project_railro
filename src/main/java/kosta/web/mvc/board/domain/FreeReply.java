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

import kosta.web.mvc.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FreeReply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "free_rno_seq")
	@SequenceGenerator(sequenceName = "free_rno_seq" , allocationSize = 1 , name = "free_rno_seq")
	private Long freeRno; // 댓글번호
	private String freeReplyContent; //댓글내용
	
	@CreationTimestamp
	private LocalDateTime freeReplyRegdate;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "free_bno")
	private FreeBoard freeBoard;
	
	
}
