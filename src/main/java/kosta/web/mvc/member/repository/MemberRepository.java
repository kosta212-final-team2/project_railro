package kosta.web.mvc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	@Query(value = "SELECT * FROM MEMBER m WHERE m.MEMBER_ID = ?1 AND m.PWD = ?2", nativeQuery = true)
	Member login(Member member);
	
	@Query(value = "SELECT * FROM MEMBER m WHERE m.MEMBER_ID = ?1", nativeQuery = true)
	Member findByMemberId(String memberId);
	
	@Query(value = "UPDATE * FROM MEMBER m WHERE m.MEMBER_ID = ?1", nativeQuery = true)
	Member updateMember(Member member);
	
	/*
	 * @Query(value = "DELETE FROM MEMBER m WHERE m.MEMBER_ID = ?1", nativeQuery =
	 * false) void deleteByMemberId(String memberId);
	 */
}
