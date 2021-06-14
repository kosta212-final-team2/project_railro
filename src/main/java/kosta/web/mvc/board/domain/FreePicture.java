package kosta.web.mvc.board.domain;

import java.time.LocalDate;

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
@Getter
@Setter
public class FreePicture {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "free_pno_seq")
	@SequenceGenerator(sequenceName = "free_pno_seq" , allocationSize = 1 , name = "free_pno_seq")
	private Long freePno; //사진번호
	private String freePicName; //사진이름
	private String freePicSaveName; //사진 저장명
	private String freePicURL; //사진 경로
	private Long freePicSize; //사진크기
	private String freePicEXT; //사진 확장자
	
	@CreationTimestamp
	private LocalDate freePicRegdate; //등록일
	
	@ManyToOne
	@JoinColumn(name = "free_bno")
	private FreeBoard freeBoard;
}
