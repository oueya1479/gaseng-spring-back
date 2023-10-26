package com.gaseng.checklist.repository;


import com.gaseng.checklist.domain.Checklist;
import com.gaseng.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChecklistRepository extends JpaRepository<Checklist,Long> {
    Checklist findByMember(Member member);
}
