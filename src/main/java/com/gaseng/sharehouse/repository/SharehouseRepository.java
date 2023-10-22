package com.gaseng.sharehouse.repository;

import com.gaseng.checklist.domain.Checklist;
import com.gaseng.member.domain.Member;
import com.gaseng.sharehouse.domain.Sharehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharehouseRepository extends JpaRepository<Sharehouse,Long> {
    Sharehouse findByMember(Member member);
}
