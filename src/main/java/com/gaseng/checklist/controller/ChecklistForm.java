package com.gaseng.checklist.controller;

import com.gaseng.checklist.domain.CheckCigarette;
import com.gaseng.checklist.domain.CheckSleepingHabit;
import com.gaseng.checklist.domain.CheckType;
import com.gaseng.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ChecklistForm {
    Member member;
    CheckSleepingHabit chkSleepingHabit;
    CheckCigarette chkCigarette;
    Date chkSleepTime;
    String chkMbti;
    String chkCallPlace;
    CheckType chkType;
}
