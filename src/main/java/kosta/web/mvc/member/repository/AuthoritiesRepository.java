package kosta.web.mvc.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import kosta.web.mvc.member.domain.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

	@Query(value = "SELECT * from AUTHORITIES a WHERE a.MEMBER_ID=?1", nativeQuery = true)
	List<Authorities> findAllByMemberId(String memberId);
	
	@Modifying
	@Query(value = "DELETE FROM authorities a WHERE a.member_id=?1", nativeQuery=true) 
	void deleteByMemberId(String memberId);
	
	@Modifying
	@Query(value = "UPDATE authorities SET ROLE='ROLE_QUIT' WHERE member_id=?1", nativeQuery = true)
	void updateAuthorities(String memberId);
}
