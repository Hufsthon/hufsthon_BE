package com.hufsthon.demo.domain.hospital.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hufsthon.demo.domain.doctor.entity.Doctor;
import com.hufsthon.demo.domain.hospital_department.entity.HospitalDepartment;
import com.hufsthon.demo.global.common.BaseEntity;

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
public class Hospital extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hospital_id")
	private Long id;

	private String hospitalName;

	@Embedded
	private Address address;

	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private HospitalType hospitalType;

	@Embedded
	private Time time;

	@OneToMany(mappedBy = "hospital")
	@Builder.Default
	private List<Doctor> doctors = new ArrayList<>();

	@OneToMany(mappedBy = "hospital")
	@Builder.Default
	private List<HospitalDepartment> hospitalDepartments = new ArrayList<>();

}
