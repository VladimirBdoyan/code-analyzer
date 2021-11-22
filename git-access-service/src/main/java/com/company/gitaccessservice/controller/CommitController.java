package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.StatisticsResponseDto;
import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.service.commit.CommitServiceImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/git-access/density")
public class CommitController {

   private final CommitServiceImpl commitService;

    public CommitController(CommitServiceImpl commitService) {
        this.commitService = commitService;
    }

    @PostMapping()
    public StatisticsResponseDto getUserReport(@RequestBody RequestDto request) {
        return commitService.createGitUserReport(request);
    }
}
