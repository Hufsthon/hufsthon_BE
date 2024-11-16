package com.hufsthon.demo.domain.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hufsthon.demo.domain.doctor.entity.Doctor;

@Repository
public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT DISTINCT d FROM Doctor d " +
		"JOIN FETCH d.doctorSpecializedDiseases dsd " +
		"JOIN FETCH dsd.disease dis " +
		"WHERE dis.name LIKE %:keyword% " +
		"OR dis.symptom LIKE %:keyword% " +
		"AND d.status = 'ACTIVE' " +
		"ORDER BY d.name ASC")
	List<Doctor> findDoctorsByDiseaseKeyword(@Param("keyword") String keyword);

}
