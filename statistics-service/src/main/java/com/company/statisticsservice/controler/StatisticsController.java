package com.company.statisticsservice.controler;


import com.company.statisticsservice.Service.CommitDensityServiceImpl;
import com.company.statisticsservice.Service.GitUserServiceImpl;
import com.company.statisticsservice.dto.GitAccessResponseDto;
import com.company.statisticsservice.dto.CommitDensityReportDto;
import com.company.statisticsservice.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {


    private final GitUserServiceImpl gitUserService;
    private final CommitDensityServiceImpl commitDensityService;

    public StatisticsController(GitUserServiceImpl gitUserService, CommitDensityServiceImpl commitDensityService) {
        this.gitUserService = gitUserService;
        this.commitDensityService = commitDensityService;
    }


    @GetMapping("/{org}/{repo}/{user}/{startDate}/{endDate}")
    public ResponseEntity<GitAccessResponseDto> getPRDensityTotal(@PathVariable String org, @PathVariable String repo,
                                                                  @PathVariable String user, @PathVariable String startDate,
                                                                  @PathVariable String endDate) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder url = new StringBuilder();

        //TODO exchange GitUserDToResponse to Optional
        GitAccessResponseDto gitUserDtoResponse = null;
        url.append(Constants.GITACCESSURL).append("/")
                .append(org).append("/")
                .append(repo).append("/")
                .append(user).append("/")
                .append(startDate).append("/")
                .append(endDate).append("/");
        try {
            gitUserDtoResponse = restTemplate.getForObject(new URI(url.toString()), GitAccessResponseDto.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert gitUserDtoResponse != null;
        gitUserDtoResponse.setLogin(user);
        return ResponseEntity.of(gitUserService.create(gitUserDtoResponse));
    }

    @GetMapping("/{userID}/getReports")
    public List<CommitDensityReportDto> getUserReport(@PathVariable Long userID){
        return commitDensityService.getCommitDensityReport(userID);
    }
}

