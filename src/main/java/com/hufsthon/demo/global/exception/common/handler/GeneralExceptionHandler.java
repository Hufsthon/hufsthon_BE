package com.hufsthon.demo.global.exception.common.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hufsthon.demo.global.common.CommonResponse;
import com.hufsthon.demo.global.exception.common.ErrorReason;
import com.hufsthon.demo.global.exception.common.GeneralException;
import com.hufsthon.demo.global.exception.common.code.BaseErrorCode;
import com.hufsthon.demo.global.exception.common.code.GlobalErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
		String errorMessage = e.getConstraintViolations().stream()
			.map(constraintViolation -> constraintViolation.getMessage())
			.findFirst()
			.orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

		return handleExceptionInternalConstraint(e, GlobalErrorCode.BAD_ARGS_ERROR, HttpHeaders.EMPTY, request);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException e,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {

		Map<String, String> errors = new LinkedHashMap<>();

		e.getBindingResult().getFieldErrors().stream()
			.forEach(fieldError -> {
				String fieldName = fieldError.getField();
				String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
				errors.merge(fieldName, errorMessage,
					(existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
			});

		return handleExceptionInternalArgs(e, HttpHeaders.EMPTY, GlobalErrorCode.BAD_ARGS_ERROR, request, errors);
	}

	@ExceptionHandler
	public ResponseEntity<Object> exception(Exception e, WebRequest request) {
		e.printStackTrace();

		return handleExceptionInternalFalse(
			e,
			GlobalErrorCode.SERVER_ERROR,
			HttpHeaders.EMPTY,
			GlobalErrorCode.SERVER_ERROR.getHttpStatus(),
			request,
			e.getMessage());
	}

	@ExceptionHandler(value = GeneralException.class)
	public ResponseEntity<Object> onThrowException(GeneralException generalException, HttpServletRequest request) {
		ErrorReason errorReason = generalException.getErrorReasonHttpStatus();
		return handleExceptionInternal(generalException, errorReason, null, request);
	}

	private ResponseEntity<Object> handleExceptionInternal(
		Exception e,
		ErrorReason reason,
		HttpHeaders headers,
		HttpServletRequest request) {

		CommonResponse<Object> body = CommonResponse.onFailure(reason.getCode(), reason.getMessage(), reason.getData());

		WebRequest webRequest = new ServletWebRequest(request);
		return super.handleExceptionInternal(
			e,
			body,
			headers,
			reason.getHttpStatus(),
			webRequest
		);
	}

	private ResponseEntity<Object> handleExceptionInternalFalse(
		Exception e,
		BaseErrorCode errorCode,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request,
		String errorPoint) {

		ErrorReason errorReason = errorCode.getReasonHttpStatus(errorPoint);
		CommonResponse<Object> body = CommonResponse.onFailure(
			errorReason.getCode(),
			errorReason.getMessage(),
			errorReason.getData());

		return super.handleExceptionInternal(
			e,
			body,
			headers,
			status,
			request
		);
	}

	private ResponseEntity<Object> handleExceptionInternalArgs(
		Exception e,
		HttpHeaders headers,
		BaseErrorCode errorCode,
		WebRequest request,
		Map<String, String> errorArgs) {

		ErrorReason errorReason = errorCode.getReasonHttpStatus(errorArgs);
		CommonResponse<Object> body = CommonResponse.onFailure(
			errorReason.getCode(),
			errorReason.getMessage(),
			errorReason.getData());

		return super.handleExceptionInternal(
			e,
			body,
			headers,
			errorReason.getHttpStatus(),
			request
		);
	}

	private ResponseEntity<Object> handleExceptionInternalConstraint(
		Exception e,
		BaseErrorCode errorCode,
		HttpHeaders headers,
		WebRequest request) {

		ErrorReason errorReason = errorCode.getReasonHttpStatus();
		CommonResponse<Object> body = CommonResponse.onFailure(
			errorReason.getCode(),
			errorReason.getMessage(),
			errorReason.getData());

		return super.handleExceptionInternal(
			e,
			body,
			headers,
			errorReason.getHttpStatus(),
			request
		);
	}
}