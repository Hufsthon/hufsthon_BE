package com.hufsthon.demo.global.exception.common.code;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import com.hufsthon.demo.global.exception.common.ErrorReason;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
	//Global
	// 500 Server Error
	SERVER_ERROR(INTERNAL_SERVER_ERROR, "GLOBAL500_1", "서버 에러, 서버 개발자에게 알려주세요."),

	// Args Validation Error
	BAD_ARGS_ERROR(BAD_REQUEST, "GLOBAL400_1", "request body의 validation이 실패했습니다. 응답 body를 참고해주세요"),

	// Auth
	// 401 Unauthorized - 권한 없음
	TOKEN_EXPIRED(UNAUTHORIZED, "AUTH401_1", "인증 토큰이 만료 되었습니다. 토큰을 재발급 해주세요"),
	INVALID_TOKEN(UNAUTHORIZED, "AUTH401_2", "인증 토큰이 유효하지 않습니다."),
	INVALID_REFRESH_TOKEN(UNAUTHORIZED, "AUTH401_3", "리프레시 토큰이 유효하지 않습니다."),
	REFRESH_TOKEN_EXPIRED(UNAUTHORIZED, "AUTH401_4", "리프레시 토큰이 만료 되었습니다."),
	AUTHENTICATION_REQUIRED(UNAUTHORIZED, "AUTH401_5", "인증 정보가 유효하지 않습니다."),
	LOGIN_REQUIRED(UNAUTHORIZED, "AUTH401_6", "로그인이 필요한 서비스입니다."),
	REFRESH_TOKEN_REQUIRED(UNAUTHORIZED, "AUTH401_7", "리프레시 토큰이 필요합니다."),
	ACCESS_TOKEN_EXPIRED(UNAUTHORIZED, "AUTH401_8", "액세스 토큰이 만료 되었습니다."),
	INVALID_ACCESS_TOKEN(UNAUTHORIZED, "AUTH401_9", "액세스 토큰이 유효하지 않습니다."),
	EMPTY_TOKEN(UNAUTHORIZED, "AUTH401_10", "토큰이 비어있습니다."),
	INVALID_SIGNATURE(UNAUTHORIZED, "AUTH401_11", "토큰의 서명이 유효하지 않습니다."),
	UNSUPPORTED_TOKEN(UNAUTHORIZED, "AUTH401_12", "지원되지 않는 토큰입니다."),
	INVALID_CREDENTIALS(UNAUTHORIZED, "AUTH401_13", "인증 정보가 올바르지 않습니다."),
	ACCOUNT_DISABLED(UNAUTHORIZED, "AUTH401_14", "계정이 비활성화 되었습니다."),
	ACCOUNT_LOCKED(UNAUTHORIZED, "AUTH401_15", "계정이 잠겼습니다."),
	ACCOUNT_EXPIRED(UNAUTHORIZED, "AUTH401_16", "계정이 만료되었습니다."),
	LOGIN_FAILED(UNAUTHORIZED, "AUTH401_17", "로그인에 실패하였습니다."),
	INVALID_AUTHENTICATION(UNAUTHORIZED, "AUTH401_18", "인증 정보가 올바르지 않습니다."),
	INVALID_LOGIN_REQUEST(BAD_REQUEST, "AUTH400_1", "로그인 요청이 올바르지 않습니다."),
	LOGOUT_FAILED(UNAUTHORIZED, "AUTH401_19", "로그아웃에 실패하였습니다."),

	// 403 Forbidden - 인증 거부
	AUTHENTICATION_DENIED(FORBIDDEN, "AUTH403_1", "인증이 거부 되었습니다."),

	// 404 Not Found - 찾을 수 없음
	REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "AUTH404_1", "리프레시 토큰이 존재하지 않습니다."),

	//Member
	// 404 Not Found - 찾을 수 없음
	MEMBER_NOT_FOUND(NOT_FOUND, "MEMBER404_1", "회원을 찾을 수 없습니다."),
	MEMBER_ALREADY_EXIST(CONFLICT, "MEMBER409_1", "이미 존재하는 회원입니다."),
	MEMBER_NOT_EXIST(CONFLICT, "MEMBER409_2", "존재하지 않는 회원입니다.")
	;



	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	@Override
	public ErrorReason getReason() {
		return ErrorReason.builder()
			.message(message)
			.code(code)
			.isSuccess(false)
			.build();
	}

	@Override
	public ErrorReason getReasonHttpStatus() {
		return ErrorReason.builder()
			.message(message)
			.code(code)
			.httpStatus(httpStatus)
			.isSuccess(false)
			.build();
	}

	@Override
	public ErrorReason getReasonHttpStatus(Object data) {
		return ErrorReason.builder()
			.message(message)
			.code(code)
			.httpStatus(httpStatus)
			.isSuccess(false)
			.data(data)  // 데이터 포함
			.build();
	}
}