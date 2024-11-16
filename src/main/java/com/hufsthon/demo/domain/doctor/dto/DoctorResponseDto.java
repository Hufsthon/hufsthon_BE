package com.hufsthon.demo.domain.doctor.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.hufsthon.demo.domain.doctor.entity.Doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorResponseDto {

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@AllArgsConstructor
	public static class DoctorListResponseDto {
		private List<DoctorDto> doctors;

		public static DoctorListResponseDto of(List<DoctorDto> doctors) {
			return DoctorListResponseDto.builder()
				.doctors(doctors)
				.build();
		}
	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	@AllArgsConstructor
	public static class DoctorDto {
		private Long id;
		private String name;
		private String imageUrl;
		private String career;
		private String specialties;
		private String hospitalName;
		private List<String> departments;
		private List<String> specializedDiseases;

		public static DoctorDto from(Doctor doctor) {
			return DoctorDto.builder()
				.id(doctor.getId())
				.name(doctor.getName())
				.imageUrl(doctor.getImageUrl())
				.career(doctor.getCareer())
				.specialties(doctor.getSpecialties())
				.hospitalName(doctor.getHospital().getHospitalName())
				.departments(doctor.getDoctorDepartments().stream()
					.map(dd -> dd.getMedicalDepartment().getName())
					.collect(Collectors.toList()))
				.specializedDiseases(doctor.getDoctorSpecializedDiseases().stream()
					.map(dsd -> dsd.getDisease().getName())
					.collect(Collectors.toList()))
				.build();
		}
	}
}