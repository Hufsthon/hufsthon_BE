package com.hufsthon.demo.domain.doctor;

import com.hufsthon.demo.domain.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT DISTINCT d FROM Doctor d " +
            "JOIN FETCH d.doctorSpecializedDiseases dsd " +
            "JOIN FETCH dsd.disease dis " +
            "WHERE dis.name LIKE %:diseaseName% " +
            "AND d.status = 'ACTIVE' " +
            "ORDER BY d.name ASC")
    List<Doctor> findDoctorsByDiseaseName(@Param("diseaseName") String diseaseName);

    @Query("SELECT DISTINCT d FROM Doctor d " +
            "LEFT JOIN FETCH d.hospital h " +
            "LEFT JOIN FETCH h.address " +
            "WHERE d.id = :doctorId AND d.status = 'ACTIVE'")
    Optional<Doctor> findByIdWithHospitalInfo(@Param("doctorId") Long doctorId);

}
