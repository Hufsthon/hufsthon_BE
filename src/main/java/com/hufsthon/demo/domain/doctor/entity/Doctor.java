package com.hufsthon.demo.domain.doctor.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hufsthon.demo.domain.doctor_department.DoctorDepartment;
import com.hufsthon.demo.domain.doctor_specialized_disease.entity.DoctorSpecializedDisease;
import com.hufsthon.demo.domain.hospital.entity.Hospital;
import com.hufsthon.demo.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Doctor extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	private String name;

	private String imageUrl;

	private String licenseNumber;

	@Lob
	private String career;

	@Lob
	private String specialties;

	@Enumerated(EnumType.STRING)
	private DoctorStatus status;

	@OneToMany(mappedBy = "doctor")
	@Builder.Default
	private List<DoctorDepartment> doctorDepartments = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	@Builder.Default
	private List<DoctorSpecializedDisease> doctorSpecializedDiseases = new ArrayList<>();

}
