package com.hufsthon.demo.auth.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hufsthon.demo.auth.member.entity.Member;

public interface MemberJPARepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(String email);

}
