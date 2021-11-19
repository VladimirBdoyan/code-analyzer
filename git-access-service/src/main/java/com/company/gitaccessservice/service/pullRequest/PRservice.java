package com.company.gitaccessservice.service.pullRequest;

import com.company.gitaccessservice.dto.pullRequest.PRFrequnecyDTO;

//This Service serve PullRequest frequency by user day,week and total it'll can changed by special dates
//Pull Request created dates is ignored , because we take it as a basis updated date for Pull request
//In whole application "user" is associated with her GitHub nickname

public interface PRservice {
    PRFrequnecyDTO getPRDensityTotal(String org, String repoName, String user);
    PRFrequnecyDTO getPRDensityWeek(String org, String repoName, String user, String date);

    // Return PullRequest Commits Density by User from whole commits in pull Requests
    PRFrequnecyDTO getPRCommitDensityTotal(String org, String repoName, String user);
    PRFrequnecyDTO getPRCommitDensityWeek(String org, String repoName, String user, String date);
}
