package com.hufsthon.demo.global.common;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hufsthon.demo.global.exception.common.code.BaseCode;
import com.hufsthon.demo.global.exception.common.code.SuccessStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "createdAt", "data"})
@Schema(description = "공통 응답", title = "공통 응답")
public class CommonResponse<T> {

	@Schema(description = "성공 여부", example = "true")
	@JsonProperty("isSuccess")
	private Boolean isSuccess;

	@Schema(description = "응답 코드", example = "200")
	@JsonProperty("code")
	private String code;

	@Schema(description = "응답 메시지", example = "요청에 성공하였습니다.")
	@JsonProperty("message")
	private String message;

	@Schema(description = "응답 생성 시간", example = "2024-10-31T17:30:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("createdAt")
	private LocalDateTime createdAt;

	@Schema(description = "응답 데이터")
	@JsonProperty("data")
	private T data;

	public static <T> CommonResponse<T> onSuccess(T data) {
		return new CommonResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(),
			LocalDateTime.now(), data);
	}

	public static <T> CommonResponse<T> of(BaseCode baseCode, T data) {
		return new CommonResponse<>(true, baseCode.getReasonHttpStatus().getCode(),
			baseCode.getReasonHttpStatus().getMessage(),
			LocalDateTime.now(), data);
	}

	public static <T> CommonResponse<T> onFailure(String code, String message, T data) {
		return new CommonResponse<>(false, code, message, LocalDateTime.now(), data);
	}
}