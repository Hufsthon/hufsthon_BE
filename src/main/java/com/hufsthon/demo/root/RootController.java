package com.hufsthon.demo.root;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hufsthon.demo.global.common.CommonResponse;

@RestController
@RequestMapping("/")
public class RootController {

	@GetMapping("/health")
	public CommonResponse<String> healthCheck() {
		return CommonResponse.onSuccess("OK");
	}
}
