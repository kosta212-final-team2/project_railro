package kosta.web.mvc.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kosta.web.mvc.member.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	
	@Query("select n from Notice n where toId=?1 and readState=0")
	List<Notice> sellectByToID(String toId);
}
