package com.gaseng.file.repository;

import com.gaseng.file.domain.File;
import com.gaseng.file.domain.ShareFile;
import com.gaseng.member.domain.Member;
import com.gaseng.sharehouse.domain.Sharehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findByMember(Member member);
    void deleteByMember(Member member);
}
