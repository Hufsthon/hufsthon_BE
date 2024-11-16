package com.hufsthon.demo.domain.hospital.entity;

import java.math.BigDecimal;

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
public class Address {

	private String postalCode;

	private String addressLine1;

	private String addressLine2;

	private BigDecimal latitude;

	private BigDecimal longitude;

}
