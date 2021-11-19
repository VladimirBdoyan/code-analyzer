package com.example.analyzedeveloper.service;

import com.example.analyzedeveloper.dto.AnalyzeStateDTO;
import com.example.analyzedeveloper.dto.mapper.AnalyzeStateMapper;
import com.example.analyzedeveloper.entity.AnalyzeState;
import com.example.analyzedeveloper.repository.AnalyzeStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyzeStateServiceImpl implements AnalyzeStateService {

    private final AnalyzeStateRepository analyzeStateRepository;

    @Override
    public AnalyzeStateDTO get(UUID jobId) {
        return AnalyzeStateMapper.mapToDTO(analyzeStateRepository.findById(jobId).orElseThrow(()->{throw new RuntimeException("Not found"); }));
    }

    @Override
    public AnalyzeState save(AnalyzeState state) {
        return analyzeStateRepository.save(state);

    }

    @Override
    public void delete(UUID jobId) {
        analyzeStateRepository.deleteById(jobId);
    }
}
