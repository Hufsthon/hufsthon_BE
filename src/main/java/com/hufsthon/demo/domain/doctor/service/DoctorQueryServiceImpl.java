package com.hufsthon.demo.domain.doctor.service;

import com.hufsthon.demo.domain.doctor.DoctorJpaRepository;
import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;
import com.hufsthon.demo.domain.doctor.entity.Doctor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorQueryServiceImpl implements DoctorQueryService {
    private final DoctorJpaRepository doctorJpaRepository;


    @Override
    public DoctorResponseDto.DoctorListResponseDto searchDoctorsByDisease(String diseaseName){
        List<Doctor> doctors = doctorJpaRepository.findDoctorsByDiseaseName(diseaseName);
        List<DoctorResponseDto.DoctorDto> doctorDtos = doctors.stream()
                .map(DoctorResponseDto.DoctorDto::from)
                .collect(Collectors.toList());

        return DoctorResponseDto.DoctorListResponseDto.builder()
                .count(doctorDtos.size())
                .doctors(doctorDtos)
                .build();
    }
}