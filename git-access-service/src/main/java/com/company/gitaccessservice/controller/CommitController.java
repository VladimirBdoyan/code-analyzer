package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.commit.CommitFrequencyDTO;
import com.company.gitaccessservice.service.commit.CommitCalcServiceImpl;
import org.kohsuke.github.GHRepositoryStatistics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/git-access")
public class CommitController {

    private final CommitCalcServiceImpl commitCalcService;

    public CommitController(CommitCalcServiceImpl commitCalcService) {
        this.commitCalcService = commitCalcService;
    }

//    @GetMapping("/{user}/{repo}/week")
//    public List<GHRepositoryStatistics.ContributorStats.Week> getUserWeekFrequency(@PathVariable String user, @PathVariable String repo) {
//        return commitCalcService.getWeekFrequency(repo, user);
//    }
//    @GetMapping("/{org}/{repo}/{user}/total")
//    public CommitFrequencyDTO getUserCommitDensity(@PathVariable String org, @PathVariable String repo,@PathVariable String user){
//        return commitCalcService.getCommitDensityTotal(org,repo,user);
//    }
    @GetMapping("/{org}/{repo}/{user}/{startDate}/{endDate}")
    public CommitFrequencyDTO getUserCommitDensityDay(@PathVariable String org, @PathVariable String repo,
                                                      @PathVariable String user, @PathVariable String startDate,
                                                      @PathVariable String endDate){
        return commitCalcService.getCommitDensity(org,repo,user,startDate,endDate);
    }
//    @GetMapping("/{org}/{repo}/{user}/{date}/week")
//    public CommitFrequencyDTO getUserCommitDensityWeek(@PathVariable String org, @PathVariable String repo, @PathVariable String user, @PathVariable String date){
//        return commitCalcService.getCommitDensityWeek(org,repo,user,date);
//    }
}
