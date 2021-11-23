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
public class AnalyzeResultServiceImpl implements AnalyzeResultService {

    private final AnalyzeResultRepository analyzeResultRepository;

    @Override
    public AnalyzeResultDTO save(AnalyzeResult analyzeResult){
        AnalyzeResult saved = analyzeResultRepository.save(analyzeResult);
        return AnalyzeResultMapper.mapToDTO(analyzeResult);
    }

    @Override
    public AnalyzeResultDTO get(Long id){
        AnalyzeResult result = analyzeResultRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("AnalyzeResult with id " + id + " not found")
                );

        return AnalyzeResultMapper.mapToDTO(result);
    }

    @Override
    public void delete(Long id) {
        analyzeResultRepository.deleteById(id);
    }
}
