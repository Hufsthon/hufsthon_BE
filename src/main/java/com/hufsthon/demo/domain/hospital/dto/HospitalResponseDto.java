package com.hufsthon.demo.domain.hospital.dto;

import com.hufsthon.demo.domain.hospital.entity.Address;
import com.hufsthon.demo.domain.hospital.entity.Hospital;
import com.hufsthon.demo.domain.hospital.entity.HospitalType;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HospitalResponseDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class HospitalDetailDto {
        private Long id;
        private String hospitalName;
        private String postalCode;
        private String addressLine1;
        private String addressLine2;
        private BigDecimal longitude;
        private BigDecimal latitude;
        private String phoneNumber;
        private HospitalType hospitalType;

        public static HospitalDetailDto from(Hospital hospital, Address address){
            return HospitalDetailDto.builder()
                    .id(hospital.getId())
                    .hospitalName(hospital.getHospitalName())
                    .postalCode(address.getPostalCode())
                    .addressLine1(address.getAddressLine1())
                    .addressLine2(address.getAddressLine2())
                    .longitude(address.getLongitude())
                    .latitude(address.getLatitude())
                    .phoneNumber(hospital.getPhoneNumber())
                    .hospitalType(hospital.getHospitalType())
                    .build();
        }
    }

}
