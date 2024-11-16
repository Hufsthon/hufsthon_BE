package com.hufsthon.demo.domain.hospital.entity;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Time {

	private LocalTime openAt;

	private LocalTime closeAt;

	private LocalTime lunchStartAt;

	private LocalTime lunchEndAt;

}
