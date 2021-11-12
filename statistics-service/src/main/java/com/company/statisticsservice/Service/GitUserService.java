package com.company.statisticsservice.Service;

import com.company.statisticsservice.dto.GitAccessResponseDto;

import java.util.Optional;

public interface GitUserService {
    Optional<GitAccessResponseDto> create(GitAccessResponseDto dto);

}
