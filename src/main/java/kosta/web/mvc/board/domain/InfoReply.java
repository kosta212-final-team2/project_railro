package kosta.web.mvc.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import kosta.web.mvc.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "info_reply")
public class InfoReply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INFO_RNO_SEQ")
	@SequenceGenerator(sequenceName = "INFO_RNO_SEQ", allocationSize = 1, name = "INFO_RNO_SEQ")
	private Long infoRno; // 댓글번호
	private String infoReplyContent; // 댓글내용
	
	@CreationTimestamp
	private LocalDateTime infoReplyRegdate; // 댓글 등록일
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "info_bno")
	private InfoBoard infoBoard;
}
