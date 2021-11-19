package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.pullRequest.PRFrequnecyDTO;
import com.company.gitaccessservice.service.pullRequest.PRservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codeAnalyzer/PR")
public class PRController {

    private final PRservice prService;
    @Autowired
    public PRController(PRservice prService) {
        this.prService = prService;
    }


    @GetMapping("/{org}/{repo}/{user}/total")
    public PRFrequnecyDTO getPRDensityTotal(@PathVariable String org, @PathVariable String repo,
                                            @PathVariable String user) {
        return prService.getPRDensityTotal(org, repo, user);
    }

    @GetMapping("/{org}/{repo}/{user}/{date}/week")
    public PRFrequnecyDTO getPRDensityTotal(@PathVariable String org, @PathVariable String repo,
                                            @PathVariable String user, @PathVariable String date) {
        return prService.getPRDensityWeek(org, repo, user, date);
    }

    @GetMapping("/{org}/{repo}/{user}/PRCommit/total")
    public PRFrequnecyDTO getPRCommitDensityTotal(@PathVariable String org, @PathVariable String repo,
                                                  @PathVariable String user) {
        return prService.getPRCommitDensityTotal(org, repo, user);
    }

    @GetMapping("/{org}/{repo}/{user}/PRCommit/{date}/week")
    public PRFrequnecyDTO getPRCommitDensityWeek(@PathVariable String org, @PathVariable String repo,
                                                 @PathVariable String user, @PathVariable String date) {
        return prService.getPRCommitDensityWeek(org, repo, user, date);
    }
}
