package com.company.statisticsservice.Service;

import com.company.statisticsservice.dto.GitUserDto;

import java.util.Optional;

public interface GitUserService {
    Optional<GitUserDto> create(GitUserDto dto);
}
