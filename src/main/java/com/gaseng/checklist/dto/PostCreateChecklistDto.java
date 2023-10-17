package com.gaseng.checklist.dto;

import com.gaseng.checklist.domain.CheckCigarette;
import com.gaseng.checklist.domain.CheckSleepingHabit;
import com.gaseng.checklist.domain.CheckType;
import com.gaseng.checklist.domain.Checklist;
import com.gaseng.member.domain.Member;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public record PostCreateChecklistDto (
 Member member,
 @NotBlank(message = "잠버릇 정도를 선택해주세요.")
 CheckSleepingHabit chkSleepingHabit,
 @NotBlank(message = "흡연 여부를 선택해주세요.")
 CheckCigarette chkCigarette,
 @NotBlank(message = "수면 시간을 선택해주세요.")
 Date chkSleepTime,
 @NotBlank(message = "MBTI를 선택해주세요.")
 String chkMbti,
 @NotBlank(message = "통화장소를 선택해주세요.")
 String chkCallPlace,
 CheckType chkType
 ) {
 public Checklist toChecklist() {
  return Checklist.builder()
          .chkSleepingHabit(chkSleepingHabit)
          .chkCigarette(chkCigarette)
          .chkSleepTime(chkSleepTime)
          .chkMbti(chkMbti)
          .chkCallPlace(chkCallPlace)
          .chkType(chkType)
          .build();
 }
}
