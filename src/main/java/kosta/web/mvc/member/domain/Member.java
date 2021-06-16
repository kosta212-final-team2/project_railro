package kosta.web.mvc.member.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {

	@Id
	private String memberId;
	
	@Column(nullable = false)
	private String pwd;
	@Column(nullable = false)
	private String addr;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String name;
	@Column(nullable = true)
	private String picture;
	@Column(nullable = false)
	private String phone;
	
}
