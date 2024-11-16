package com.hufsthon.demo.domain.doctor.service.query;

import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;

public interface DoctorQueryService {
	DoctorResponseDto.DoctorListResponseDto searchDoctors(String symptom);
}
