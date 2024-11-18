package com.hufsthon.demo.global.exception.common.code;

import com.hufsthon.demo.global.exception.common.Reason;

public interface BaseCode {

	Reason getReason();

	Reason getReasonHttpStatus();

}
