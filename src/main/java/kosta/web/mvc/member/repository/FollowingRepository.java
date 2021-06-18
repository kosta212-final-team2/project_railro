package kosta.web.mvc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.member.domain.Following;

public interface FollowingRepository extends JpaRepository<Following, Long> {
	@Modifying
	@Query("delete from Following f where f.fromId=?1 and f.toId=?2")
	void deleteByFromIdAndToId(String fromId, String toId);
	
	@Query("select f from Following f where f.fromId=?1 and f.toId=?2")
	Following findByFromIdAndToId(String fromId, String toId);
}
