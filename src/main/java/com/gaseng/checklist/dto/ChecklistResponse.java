package com.gaseng.checklist.dto;

import com.gaseng.checklist.domain.CheckCigarette;
import com.gaseng.checklist.domain.CheckSleepingHabit;
import com.gaseng.checklist.domain.CheckType;
import com.gaseng.member.domain.Member;

import javax.persistence.*;
import java.util.Date;

public record ChecklistResponse (
    Long id,
    CheckSleepingHabit chkSleepingHabit,
    CheckCigarette chkCigarette,
    Date chkSleepTime,
    String chkMbti,
    String chkCallPlace,
    CheckType chkType

){

}
