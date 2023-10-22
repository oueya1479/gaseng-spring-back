package com.gaseng.file.repository;

import com.gaseng.file.domain.ShareFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareFileRepository extends JpaRepository<ShareFile,Long> {

}
