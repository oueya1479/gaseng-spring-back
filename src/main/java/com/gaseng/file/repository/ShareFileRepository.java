package com.gaseng.file.repository;

import com.gaseng.file.domain.ShareFile;
import com.gaseng.sharehouse.domain.Sharehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareFileRepository extends JpaRepository<ShareFile, Long> {
    List<ShareFile> findBySharehouse(Sharehouse sharehouse);
}
