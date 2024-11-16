package com.hufsthon.demo.domain.doctor.controller;

import com.hufsthon.demo.domain.doctor.dto.DoctorResponseDto;
import com.hufsthon.demo.domain.doctor.service.DoctorQueryService;
import com.hufsthon.demo.global.common.CommonResponse;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorQueryService doctorQueryService;

    @GetMapping("/{doctorId}")
    public ResponseEntity<CommonResponse<DoctorResponseDto.DoctorDetailResponseDto>> getDoctorDetail(
            @PathVariable("doctorId") Long doctorId) {

            DoctorResponseDto.DoctorDetailResponseDto response =
                    doctorQueryService.getDoctorDetail(doctorId);
            return ResponseEntity.ok(CommonResponse.onSuccess(response));
    }
}
