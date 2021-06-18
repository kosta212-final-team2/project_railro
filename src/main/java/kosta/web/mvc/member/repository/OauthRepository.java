package kosta.web.mvc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.member.domain.OauthId;

public interface OauthRepository extends JpaRepository<OauthId, String> {

	
	@Query(value= "select * from oauth_id where naver_id=?1", nativeQuery = true)
	OauthId login(String oauthId);
	
	@Modifying
	@Query("DELETE FROM oauth_id  WHERE memberId = ?1") 
	void deleteByMemberId(String memberId);
}
