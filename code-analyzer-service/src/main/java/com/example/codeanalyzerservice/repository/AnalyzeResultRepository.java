package com.example.findbugs.repository;

import com.example.findbugs.entity.AnalyzeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeResultRepository extends JpaRepository<AnalyzeResult,Long> {
}
