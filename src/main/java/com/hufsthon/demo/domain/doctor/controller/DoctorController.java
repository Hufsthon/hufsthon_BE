package com.hufsthon.demo.domain.doctor.controller;

import com.hufsthon.demo.domain.doctor.service.DoctorQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("doctors")
public class DoctorController {
    private final DoctorQueryService doctorQueryService;


}
