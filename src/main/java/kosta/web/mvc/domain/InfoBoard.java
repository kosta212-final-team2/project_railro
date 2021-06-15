package kosta.web.mvc.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private int infoReadnum; // 조회수
	private int infoVote; // 추천수
	private String memberId; // 작성자 아이디
	
	@CreationTimestamp
	private LocalDateTime infoRegdate; // 등록일
}
