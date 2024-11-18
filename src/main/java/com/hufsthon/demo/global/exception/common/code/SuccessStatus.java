package com.hufsthon.demo.global.exception.common.code;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import com.hufsthon.demo.global.exception.common.Reason;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
	// 일반적인 응답
	_OK(OK, "COMMON200", "성공입니다."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	@Override
	public Reason getReason() {
		return Reason.builder()
			.isSuccess(true)
			.code(code)
			.message(message)
			.build();
	}

	@Override
	public Reason getReasonHttpStatus() {
		return Reason.builder()
			.isSuccess(true)
			.code(code)
			.message(message)
			.httpStatus(httpStatus)
			.build();
	}
}
