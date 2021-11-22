package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.entity.CodeSmell;
import com.example.codeanalyzerservice.exception.ResourceNotFoundException;
import com.example.codeanalyzerservice.repository.CodeSmellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeSmellService {

    private final CodeSmellRepository codeSmellRepository;

    CodeSmell save(CodeSmell bug) {
        return codeSmellRepository.save(bug);
    }

    CodeSmell getById(Long id) {
        return codeSmellRepository
                .findById(id)
                .orElseThrow(() ->
                     new ResourceNotFoundException("CodeSmell with id " + id + " not found")
                );
    }
}
