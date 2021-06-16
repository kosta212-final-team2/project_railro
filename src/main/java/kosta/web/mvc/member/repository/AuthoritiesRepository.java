package kosta.web.mvc.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kosta.web.mvc.member.domain.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

	@Query(value = "SELECT * from AUTHORITIES a WHERE a.MEMBER_ID=?1", nativeQuery = true)
	List<Authorities> findAllByMemberId(String memberId);
	
	/*
	 * @Query(value = "DELETE FROM AUTHORITIES a WHERE a.MEMBER_ID = ?1") void
	 * deleteByMemberId(String memberId);
	 */
}
