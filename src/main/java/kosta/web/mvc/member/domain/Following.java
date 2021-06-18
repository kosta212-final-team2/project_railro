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
public class Following {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOLLOWING_SEQ")
	@SequenceGenerator(sequenceName = "FOLLOWING_SEQ", allocationSize = 1, name = "FOLLOWING_SEQ")
	private Long followingNo;
	
	private String fromId;
	
	private String toId;
	
	@CreationTimestamp
	private LocalDateTime regdate;
}
