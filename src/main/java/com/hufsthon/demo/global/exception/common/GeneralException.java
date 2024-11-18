package com.hufsthon.demo.global.exception.common;

import com.hufsthon.demo.global.exception.common.code.BaseErrorCode;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

	private final BaseErrorCode errorCode;

	public GeneralException(BaseErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorReason getErrorReason() {
		return this.errorCode.getReason();
	}

	public ErrorReason getErrorReasonHttpStatus() {
		return this.errorCode.getReasonHttpStatus();
	}
}