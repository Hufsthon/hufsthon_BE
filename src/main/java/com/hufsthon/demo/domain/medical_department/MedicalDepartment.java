package com.hufsthon.demo.domain.medical_department;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hufsthon.demo.domain.department_disease.entity.DepartmentDisease;
import com.hufsthon.demo.domain.doctor_department.DoctorDepartment;
import com.hufsthon.demo.domain.hospital_department.entity.HospitalDepartment;
import com.hufsthon.demo.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
public class MedicalDepartment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medical_department_id")
	private Long id;

	private String name;

	@Lob
	private String description;

	@OneToMany(mappedBy = "medicalDepartment")
	@Builder.Default
	private List<HospitalDepartment> hospitalDepartments = new ArrayList<>();

	@OneToMany(mappedBy = "medicalDepartment")
	@Builder.Default
	private List<DoctorDepartment> doctorDepartments = new ArrayList<>();

	@OneToMany(mappedBy = "medicalDepartment")
	@Builder.Default
	private List<DepartmentDisease> departmentDiseases = new ArrayList<>();
	
}
