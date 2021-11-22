package com.company.statisticsservice.controler;


import com.company.statisticsservice.service.commitDensityService.CommitDensityServiceImpl;
import com.company.statisticsservice.dto.RequestDto;

import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.service.statisticsService.StatisticsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {

    private final CommitDensityServiceImpl commitDensityService;
    private final StatisticsServiceImpl statisticsService;

    public StatisticsController(CommitDensityServiceImpl commitDensityService, StatisticsServiceImpl statisticsService) {
        this.commitDensityService = commitDensityService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{userID}/getReports")
    public List<StatisticsReportDto> getUserCommitDensityReport(@PathVariable Long userID) {
        return commitDensityService.getCommitDensityReports(userID);
    }

    @PostMapping()
    public StatisticsReportDto getPRDensityTotal(@RequestBody RequestDto request) {
        return statisticsService.createStatisticsReport(request);
    }
}