package com.hufsthon.demo.auth.refreshtoken.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hufsthon.demo.auth.member.entity.Member;
import com.hufsthon.demo.auth.member.repository.MemberJPARepository;
import com.hufsthon.demo.auth.refreshtoken.entity.RefreshToken;
import com.hufsthon.demo.auth.refreshtoken.repository.RefreshTokenJPARepository;
import com.hufsthon.demo.global.exception.common.code.GlobalErrorCode;
import com.hufsthon.demo.global.exception.member.MemberException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenCommandServiceImpl implements RefreshTokenCommandService {

	private final RefreshTokenJPARepository refreshTokenJPARepository;
	private final MemberJPARepository memberJPARepository;

	@Override
	public void saveNewRefreshToken(String email, String refreshToken) {
		Member member = memberJPARepository.findByEmail(email)
			.orElseThrow(() -> new MemberException(GlobalErrorCode.MEMBER_NOT_FOUND));

		refreshTokenJPARepository.deleteByMemberId(member.getId());

		refreshTokenJPARepository.save(RefreshToken.createRefreshToken(refreshToken, email, member));

		log.info("Refresh Token saved for email: {}", email);

	}

	@Override
	public void removeRefreshToken(String refreshToken) {
		refreshTokenJPARepository.deleteByRefreshToken(refreshToken);

	}
}