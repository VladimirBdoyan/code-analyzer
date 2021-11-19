package com.example.analyzedeveloper.repository;

import com.example.analyzedeveloper.entity.AnalyzeState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnalyzeStateRepository extends JpaRepository<AnalyzeState, UUID> {
}
