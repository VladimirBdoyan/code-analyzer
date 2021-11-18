package com.example.findbugs.service;

import com.example.findbugs.dto.AnalyzeResultDTO;
import com.example.findbugs.dto.mapper.AnalyzeResultMapper;
import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.repository.AnalyzeResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeResultService {

    private final AnalyzeResultRepository analyzeResultRepository;

    public AnalyzeResultDTO getById(Long id){
        AnalyzeResult result=analyzeResultRepository.findById(id).orElseThrow(()->new RuntimeException("Nut found"));
        return AnalyzeResultMapper.mapToDTO(result);
    }

    public AnalyzeResult save(AnalyzeResult analyzeResult){
        return analyzeResultRepository.save(analyzeResult);
    }


}
