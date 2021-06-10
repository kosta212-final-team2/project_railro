package kosta.web.mvc.board.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "free_bno_seq")
	@SequenceGenerator(sequenceName = "free_bno_seq" , allocationSize =1 ,name = "free_bno_seq")
	private Long freeBno; // 글번호
	private String freeSubject;// 글제목
	private String memberId;// 작성자
	private String freeContent;// 내용
	private int freeReadnum; // 조회수
	private int freeVote; // 추천수
	@CreationTimestamp
	private LocalDateTime freeRegdate; // 등록일
	
	/**
	 * 부모글의에 해당하는 댓글정보 
	 * cascade = CascadeType.ALL 옵션은 만약 Entity의 상태변화가 생기면 연관관계가 있는 Entity도 상태변화를 전이시키는 옵션 
	 * */
	@OneToMany(mappedBy = "freeBoard" , cascade = CascadeType.ALL)
	private List<FreeReply> freeReplyList;
	
	public FreeBoard(Long freeBno) {
		this.freeBno= freeBno;
	}
	
}
