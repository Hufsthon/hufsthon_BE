package com.hufsthon.demo.global.exception.jwt;

import com.hufsthon.demo.global.exception.common.GeneralException;
import com.hufsthon.demo.global.exception.common.code.BaseErrorCode;

public class JwtException extends GeneralException {
	public JwtException(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
