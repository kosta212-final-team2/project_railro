package kosta.web.mvc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
