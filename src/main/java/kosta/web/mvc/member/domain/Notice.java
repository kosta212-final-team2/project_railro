package kosta.web.mvc.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ")
	@SequenceGenerator(sequenceName = "NOTICE_SEQ", allocationSize = 1, name = "NOTICE_SEQ")
	private Long noticeNo;
	
	private String message;
	
	private String fromId;
	
	private String toId;
	
	@CreationTimestamp
	private LocalDateTime regdate;
}
