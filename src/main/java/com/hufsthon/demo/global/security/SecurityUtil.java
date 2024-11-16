package com.hufsthon.demo.global.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hufsthon.demo.auth.member.dto.CustomUserDetails;
import com.hufsthon.demo.global.exception.common.GeneralException;
import com.hufsthon.demo.global.exception.common.code.GlobalErrorCode;

import jakarta.servlet.http.Cookie;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SecurityUtil {

	/**
	 * SecurityContext에서 현재 인증된 사용자의 이메일을 가져옵니다.
	 * @return 인증된 사용자의 이메일
	 * @throws GeneralException 인증 정보가 없거나 유효하지 않은 경우
	 */
	public static String getCurrentUserEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			throw new GeneralException(GlobalErrorCode.AUTHENTICATION_REQUIRED);
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof CustomUserDetails) {
			return ((CustomUserDetails)principal).getUsername();
		} else if (principal instanceof String) {
			return (String)principal;
		}

		throw new GeneralException(GlobalErrorCode.INVALID_AUTHENTICATION);
	}

	/**
	 * SecurityContext에서 현재 인증된 CustomUserDetails를 가져옵니다.
	 * @return 인증된 사용자의 CustomUserDetails
	 * @throws GeneralException 인증 정보가 없거나 유효하지 않은 경우
	 */
	public static CustomUserDetails getCurrentUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated() ||
			authentication.getPrincipal().equals("anonymousUser")) {
			throw new GeneralException(GlobalErrorCode.AUTHENTICATION_REQUIRED);
		}

		return (CustomUserDetails)authentication.getPrincipal();
	}

	public Cookie createCookie(String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		cookie.setHttpOnly(true);
		return cookie;
	}
}
