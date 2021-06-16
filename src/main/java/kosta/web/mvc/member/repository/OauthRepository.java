package kosta.web.mvc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.member.domain.OauthId;

public interface OauthRepository extends JpaRepository<OauthId, String> {

	
	@Query(value= "select * from oauth_id where naver_id=?1")
	OauthId login(String oauthId);
	
}
