package com.hufsthon.demo.domain.doctor.service;

import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;

public interface DoctorQueryService {
    DoctorResponseDto.DoctorListResponseDto searchDoctorsByDisease(String diseaseName);
}
