package com.hufsthon.demo.global.exception.common.code;

import com.hufsthon.demo.global.exception.common.ErrorReason;

public interface BaseErrorCode {

	public ErrorReason getReason();

	public ErrorReason getReasonHttpStatus();

	ErrorReason getReasonHttpStatus(Object data);
}