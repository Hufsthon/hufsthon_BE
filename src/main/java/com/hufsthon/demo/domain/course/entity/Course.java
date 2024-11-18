package com.hufsthon.demo.domain.course.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hufsthon.demo.domain.category.entity.Category;
import com.hufsthon.demo.domain.sports_center.entity.SportsCenter;
import com.hufsthon.demo.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Course extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category; // 카테고리

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sports_center_id")
	private SportsCenter sportsCenter; // 운동장

	private String courseName; // 강의 이름

	private String courseLink; // 강의 홈페이지 링크

	private BigDecimal price; // 강의료

	private LocalTime startTime; // 강의 시작 시간

	private LocalTime endTime; // 강의 종료 시간

	private LocalDate startDate; // 강의 시작일

	private LocalDate endDate; // 강의 종료일

	private Status status; // 운영 상태(모집중, 마감, 종료 등)

	private Integer currentEnrollment; // 현재 등록 인원

	private Integer capacity; // 정원수

}
