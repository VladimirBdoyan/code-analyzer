package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeStateDTO;
import com.example.masterservice.dto.mapper.AnalyzeStateMapper;
import com.example.masterservice.entity.AnalyzeState;
import com.example.masterservice.exception.ResourceNotFoundException;
import com.example.masterservice.repository.AnalyzeStateRepository;
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
        return AnalyzeStateMapper.mapToDTO(analyzeStateRepository
                .findById(jobId)
                .orElseThrow(()->{
                    throw new ResourceNotFoundException("AnalyzeState with jobId " + jobId + " not found");
                }));
    }

    @Override
    public AnalyzeState save(AnalyzeState analyzeState) {
        return analyzeStateRepository.save(analyzeState);

    }

    @Override
    public void delete(UUID jobId) {
        analyzeStateRepository.deleteById(jobId);
    }
}
