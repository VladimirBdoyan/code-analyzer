package com.example.security.service;

import com.example.security.model.CodeEntity;
import com.example.security.repository.CodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public void add(CodeEntity codeEntity) {
        Optional<CodeEntity> codeEntityOptional = get(codeEntity.getUsername());
        if (codeEntityOptional.isPresent()) {
            codeEntityOptional.get().setCode(codeEntity.getCode());
            codeEntityOptional.get().setExpireTime(codeEntity.getExpireTime());
            codeRepository.save(codeEntityOptional.get());
        } else {
            codeRepository.save(codeEntity);
        }
    }

    public Optional<CodeEntity> get(String username) {
        return Optional.ofNullable(codeRepository.findByUsername(username));
    }

    public boolean isAccessed(String username, String code) {
        Optional<CodeEntity> codeEntityOptional = get(username);
        return codeEntityOptional.isPresent() &&
                !LocalDateTime.now().isAfter(codeEntityOptional.get().getExpireTime()) &&
                codeEntityOptional.get().getCode().equals(code);
    }
}
