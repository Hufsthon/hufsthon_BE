package com.hufsthon.demo.domain.doctor.controller;

import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;
import com.hufsthon.demo.domain.doctor.service.DoctorQueryService;
import com.hufsthon.demo.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorQueryService doctorQueryService;

    @GetMapping("/{doctorId}")
    public CommonResponse<DoctorResponseDto.DoctorDetailResponseDto> getDoctorDetail(
            @PathVariable("doctorId") Long doctorId) {
        DoctorResponseDto.DoctorDetailResponseDto response =
                doctorQueryService.getDoctorDetail(doctorId);

        return CommonResponse.onSuccess(response);
    }
}