package com.hufsthon.demo.domain.sports_center.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hufsthon.demo.domain.address.jibunaddress.JibunAddress;
import com.hufsthon.demo.domain.address.roadaddress.RoadAddress;
import com.hufsthon.demo.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class SportsCenter extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sports_center_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "road_address_id")
	private RoadAddress roadAddress; // 도로명 주소

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jibun_address_id")
	private JibunAddress jibunAddress; // 지번 주소

	private String centerName; // 센터 이름

	private String phoneNumber; // 전화번호

}
