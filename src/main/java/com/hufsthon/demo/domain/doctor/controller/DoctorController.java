package com.hufsthon.demo.domain.doctor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;
import com.hufsthon.demo.domain.doctor.service.query.DoctorQueryService;
import com.hufsthon.demo.global.common.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
	private final DoctorQueryService doctorQueryService;

	@GetMapping("/search")
	public CommonResponse<DoctorResponseDto.DoctorListResponseDto> getDoctorsByDiseaseName(
		@RequestParam String symptom) {
		return CommonResponse.onSuccess(doctorQueryService.searchDoctors(symptom));
	}
}
