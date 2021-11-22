package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.dto.AnalyzeResultDTO;
import com.example.codeanalyzerservice.dto.mapper.AnalyzeResultMapper;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.exception.ResourceNotFoundException;
import com.example.codeanalyzerservice.repository.AnalyzeResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeResultService {

    private final AnalyzeResultRepository analyzeResultRepository;

    public AnalyzeResultDTO getById(Long id){
        AnalyzeResult result = analyzeResultRepository
                .findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("AnalyzeResult with id " + id + " not found"));
        return AnalyzeResultMapper.mapToDTO(result);
    }

    public AnalyzeResult save(AnalyzeResult analyzeResult){
        return analyzeResultRepository.save(analyzeResult);
    }


}
