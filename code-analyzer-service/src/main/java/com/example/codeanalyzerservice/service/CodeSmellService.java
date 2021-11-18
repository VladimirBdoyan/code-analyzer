package com.example.findbugs.service;

import com.example.findbugs.entity.CodeSmell;
import com.example.findbugs.exception.ResourceNotFoundException;
import com.example.findbugs.repository.CodeSmellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeSmellService {

    private final CodeSmellRepository bugEntityRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    CodeSmell save(CodeSmell bug) {
        return bugEntityRepository.save(bug);
    }

    CodeSmell getById(Long id) {
        return bugEntityRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("BugEntity with id " + id + " not found");
                });
    }
}
