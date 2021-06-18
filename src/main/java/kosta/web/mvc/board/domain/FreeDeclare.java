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
public class FreeDeclare {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "free_dno_seq")
	@SequenceGenerator(sequenceName = "free_dno_seq" , allocationSize = 1 , name = "free_dno_seq")
	private Long freeDno; //신고번호
	private String freeReason; //신고사유
	private String memberId; //신고 누른 사람
	private int freeState; //처리 상태
	
	@CreationTimestamp
	private LocalDateTime freeDecDate; //신고일
	
	@ManyToOne
	@JoinColumn(name = "free_bno")
	private FreeBoard freeBoard;
	
}
