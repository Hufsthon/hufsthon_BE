package com.hufsthon.demo.global.exception.common;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Reason {
	private final boolean isSuccess;
	private final String code;
	private final String message;
	private HttpStatus httpStatus;

}
