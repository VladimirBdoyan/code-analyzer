package com.company.statisticsservice.service.statisticsService;

import com.company.statisticsservice.dto.RequestDto;
import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;

public interface StatisticsService {
    StatisticsReportDto createStatisticsReport(RequestDto request);
}
