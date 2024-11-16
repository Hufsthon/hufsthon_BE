package com.hufsthon.demo.domain.doctor.dto;

import com.hufsthon.demo.domain.doctor.entity.Doctor;
import lombok.*;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class DoctorListResponseDto {
        private int count;
        private List<DoctorDto> doctors;

        public static DoctorListResponseDto of(List<DoctorDto> doctors) {
        return DoctorListResponseDto.builder()
                .count(doctors.size())
                .doctors(doctors)
                .build();

        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class DoctorDto {
        private Long id;
        private String name;
        private String imageUrl;
        private String specialties;
        private String hospitalName;

        public static DoctorDto from(Doctor doctor) {
            return DoctorDto.builder()
                    .id(doctor.getId())
                    .name(doctor.getName())
                    .imageUrl(doctor.getImageUrl())
                    .specialties(doctor.getSpecialties())
                    .hospitalName(doctor.getHospital().getHospitalName())
                    .build();
        }
    }
}
