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
public class FreeRecommend {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "free_vno_seq")
	@SequenceGenerator(sequenceName = "free_vno_seq" , allocationSize = 1 , name = "free_vno_seq")
	private Long freeVno; //추천번호
	private String memberId; //추천 클리 ID
	
	@CreationTimestamp
	private LocalDateTime freeVoteDate; //추천일
	
	@ManyToOne
	@JoinColumn(name = "free_bno")
	private FreeBoard freeBoard;
}
