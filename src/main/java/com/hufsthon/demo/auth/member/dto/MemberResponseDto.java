package com.hufsthon.demo.auth.member.dto;


import java.util.List;

import com.hufsthon.demo.auth.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDto {



	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED) // access level 수정
	@AllArgsConstructor
	@Schema(description = "회원 요약 정보")
	public static class MemberSummaryDTO {
		@Schema(description = "회원 ID", example = "1")
		private Long id;

		@Schema(description = "이름", example = "동규")
		private String firstName;

		@Schema(description = "성", example = "박")
		private String lastName;

		@Schema(description = "이메일", example = "test@example.com")
		private String email;

		public static MemberSummaryDTO of(Member member) {
			return MemberSummaryDTO.builder()
				.id(member.getId())
				.email(member.getEmail())
				.build();
		}

	}

	@Getter
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Schema(description = "회원 요약 정보 리스트")
	public static class MemberListResponseDTO {
		@Schema(description = "전체 회원 수", example = "5")
		private int totalCount;

		@Schema(description = "회원 목록")
		private List<MemberSummaryDTO> memberSummaryDTOS;

		public static MemberListResponseDTO of(List<Member> members) {
			return MemberListResponseDTO.builder()
				.totalCount(members.size())
				.memberSummaryDTOS(members.stream()
					.map(MemberSummaryDTO::of)
					.toList())
				.build();
		}
	}
}