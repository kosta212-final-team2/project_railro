package kosta.web.mvc.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.web.mvc.member.domain.Authorities;
import kosta.web.mvc.member.domain.Member;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

}
