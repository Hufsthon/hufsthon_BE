package com.hufsthon.demo.domain.course.entity;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
	ACTIVE("활성화", "A"),
	INACTIVE("비활성화", "I"),
	PENDING("대기중", "P"),
	DELETED("삭제됨", "D"),
	;

	private final String description;

	private final String code;

	public static Status fromCode(String code) {
		return Arrays.stream(values())
			.filter(status -> status.code.equals(code))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid Status code: " + code));
	}
}
