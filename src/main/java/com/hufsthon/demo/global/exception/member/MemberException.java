package com.hufsthon.demo.global.exception.member;

import com.hufsthon.demo.global.exception.common.GeneralException;
import com.hufsthon.demo.global.exception.common.code.BaseErrorCode;

public class MemberException extends GeneralException {

	public MemberException(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
