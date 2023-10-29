package com.gaseng.sharehouse.repository;

import com.gaseng.sharehouse.domain.Sharehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SharehouseRepository extends JpaRepository<Sharehouse,Long> {
    Optional<Sharehouse> findByShrId(Long shrId);

}
