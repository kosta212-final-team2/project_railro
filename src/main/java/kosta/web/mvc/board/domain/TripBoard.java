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
@Table(name = "trip_board")
public class TripBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRIP_BNO_SEQ")
	@SequenceGenerator(sequenceName = "TRIP_BNO_SEQ", allocationSize = 1, name = "TRIP_BNO_SEQ")
	private Long tripBno; // 글번호
	private String tripSubject; // 제목
	private String tripContent; // 내용
	private String memberId; // 작성자 아이디
	
	@CreationTimestamp
	private LocalDateTime tripRegdate; // 등록일
	private int tripReadnum; // 조회수
	private int tripVote; // 추천수
	
	@OneToMany(mappedBy = "tripBoard", cascade = CascadeType.ALL) // 지연로딩
	private List<TripReply> tripReplyList;
	
	public TripBoard(Long tripBno) {
		this.tripBno = tripBno;
	}
	
}
