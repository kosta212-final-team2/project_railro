package kosta.web.mvc.member.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorities_no_seq")
	@SequenceGenerator(sequenceName = "authorities_no_seq", allocationSize = 1, name = "authorities_no_seq")
	private Long no;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "memberId")
	private Member member;
	
	@Column(nullable = false)
	private String role;
}
