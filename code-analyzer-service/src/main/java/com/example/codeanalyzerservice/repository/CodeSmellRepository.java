package com.example.findbugs.repository;

import com.example.findbugs.entity.CodeSmell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeSmellRepository extends JpaRepository<CodeSmell, Long> {
}
