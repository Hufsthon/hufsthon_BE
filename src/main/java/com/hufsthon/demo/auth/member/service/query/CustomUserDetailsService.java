package com.hufsthon.demo.auth.member.service.query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hufsthon.demo.auth.member.dto.CustomUserDetails;
import com.hufsthon.demo.auth.member.entity.Member;
import com.hufsthon.demo.auth.member.repository.MemberJPARepository;
import com.hufsthon.demo.global.exception.common.code.GlobalErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberJPARepository memberJPARepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Member member = memberJPARepository.findByEmail(email)
			.orElseThrow(
				() -> new UsernameNotFoundException(GlobalErrorCode.MEMBER_NOT_FOUND.getMessage())
			);

		return new CustomUserDetails(member);

	}
}

