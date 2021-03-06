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
import javax.persistence.Table;

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
@Table(name = "info_board")
public class InfoBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INFO_BNO_SEQ")
	@SequenceGenerator(sequenceName = "INFO_BNO_SEQ", allocationSize = 1, name = "INFO_BNO_SEQ")
	private Long infoBno; // 글번호
	private String infoSubject; // 제목
	private String infoContent; // 내용
	private String memberId; // 작성자 아이디
	
	@CreationTimestamp
	private LocalDateTime infoRegdate; // 등록일
	private int infoReadnum; // 조회수
	private int infoVote; // 추천수
	
	@OneToMany(mappedBy = "infoBoard", cascade = CascadeType.ALL) // 지연로딩
	private List<InfoReply> infoReplyList;
	
	public InfoBoard(Long infoBno) {
		this.infoBno = infoBno;
	}
}
