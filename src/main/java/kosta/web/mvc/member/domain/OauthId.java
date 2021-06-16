package kosta.web.mvc.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="oauth_id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OauthId {

	@Id
	private String naverId;
	
	@Column(nullable = false)
	private String memberId;
	@Column(nullable = true)
	private int refreshToken;
	@Column(nullable = false)
	private int accessToken;
}
