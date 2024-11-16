package com.hufsthon.demo.domain.doctor.service.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;
import com.hufsthon.demo.domain.doctor.entity.Doctor;
import com.hufsthon.demo.domain.doctor.repository.DoctorJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorQueryServiceImpl implements DoctorQueryService {
	private final DoctorJpaRepository doctorJpaRepository;

	@Override
	public DoctorResponseDto.DoctorListResponseDto searchDoctors(String symptom) {
		List<Doctor> doctors = doctorJpaRepository.findDoctorsByDiseaseKeyword(symptom);
		return DoctorResponseDto.DoctorListResponseDto.builder()
			.doctors(doctors.stream()
				.map(DoctorResponseDto.DoctorDto::from)
				.collect(Collectors.toList()))
			.build();
	}
}