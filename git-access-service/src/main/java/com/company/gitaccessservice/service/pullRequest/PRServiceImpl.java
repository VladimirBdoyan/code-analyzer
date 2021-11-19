package com.company.gitaccessservice.service.pullRequest;

import com.company.gitaccessservice.dto.pullRequest.PRFrequnecyDTO;
import com.company.gitaccessservice.model.GitOrganization;
import com.company.gitaccessservice.service.PullRequestInfo;
import com.company.gitaccessservice.util.GitHubNaming;
import com.company.gitaccessservice.util.costant.Calculate;
import com.company.gitaccessservice.util.costant.TimeStaps;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PRServiceImpl implements PRservice {

    private final PullRequestInfo pullRequestInfo;

    @Autowired
    public PRServiceImpl(PullRequestInfo pullRequestInfo) {
        this.pullRequestInfo = pullRequestInfo;
    }

    private int repoPRCount;
    private int userPRCount;

    @Override
    public PRFrequnecyDTO getPRDensityTotal(String org, String repoName, String user) {
        List<GHPullRequest> pullRequests;
        try {
            pullRequests = setRepo(org, repoName).getPullRequests(GHIssueState.ALL);
            repoPRCount = pullRequests.size();
            pullRequests = pullRequests.stream()
                    .filter(pullRequest -> isUser(pullRequest,user))
                    .collect(Collectors.toList());
            userPRCount = pullRequests.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PRFrequnecyDTO(repoName, GitHubNaming.setUserName(user), repoPRCount, userPRCount, Calculate.density(userPRCount, repoPRCount));
    }

    @Override
    public PRFrequnecyDTO getPRDensityWeek(String org, String repoName, String user, String date) {
        LocalDate currentDay = LocalDate.parse(date);
        long dayBeforeEpoch = currentDay.getLong(ChronoField.EPOCH_DAY) * TimeStaps.TIMSTEPOFDAY - TimeStaps.TIMSTEPOF3HOURS;
        long dayAfterEpoch = currentDay.plusWeeks(1).getLong(ChronoField.EPOCH_DAY) * TimeStaps.TIMSTEPOFDAY - TimeStaps.TIMSTEPOF3HOURS;

        List<GHPullRequest> pullRequests = new ArrayList<>();
        List<GHPullRequest> pullRequestsWeekByUser = new ArrayList<>();
        try {
            pullRequests = setRepo(org, repoName).getPullRequests(GHIssueState.ALL);
            pullRequests = pullRequests.stream()
                    .filter(pr -> isBetween(pr, dayBeforeEpoch, dayAfterEpoch))
                    .collect(Collectors.toList());
            pullRequestsWeekByUser = pullRequests.stream()
                    .filter(pr -> isUser(pr,user))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        repoPRCount = pullRequests.size();
        userPRCount = pullRequestsWeekByUser.size();
        return new PRFrequnecyDTO(repoName, GitHubNaming.setUserName(user), repoPRCount, userPRCount, Calculate.density(userPRCount, repoPRCount));
    }

    @Override
    public PRFrequnecyDTO getPRCommitDensityTotal(String org, String repoName, String user) {
        List<GHRepositoryStatistics.ContributorStats> contributorStats;
        try {
            contributorStats = new GHRepositoryStatistics(setRepo(org, repoName)).getContributorStats().toList();
            userPRCount = setContribution(contributorStats).get(user);
            for (Integer rv : setContribution(contributorStats).values()) {
                repoPRCount += rv;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new PRFrequnecyDTO(repoName, GitHubNaming.setUserName(user), repoPRCount, userPRCount, Calculate.density(userPRCount, repoPRCount));
    }

    @Override
    public PRFrequnecyDTO getPRCommitDensityWeek(String org, String repoName, String user, String date) {
        LocalDate currentDay = LocalDate.parse(date);
        long dayBeforeEpoch = currentDay.getLong(ChronoField.EPOCH_DAY) * TimeStaps.TIMSTEPOFDAY - TimeStaps.TIMSTEPOF3HOURS;
        long dayAfterEpoch = currentDay.plusWeeks(1).getLong(ChronoField.EPOCH_DAY) * TimeStaps.TIMSTEPOFDAY - TimeStaps.TIMSTEPOF3HOURS;
        List<GHRepositoryStatistics.ContributorStats> contributorStats;
        try {
            contributorStats = new GHRepositoryStatistics(setRepo(org, repoName))
                    .getContributorStats()
                    .toList()
                    .stream().filter(cs -> contributorStatIsBetween(cs,dayBeforeEpoch,dayAfterEpoch))
                    .collect(Collectors.toList());
            userPRCount = setContribution(contributorStats).get(user);
            for (Integer rv : setContribution(contributorStats).values()) {
                repoPRCount += rv;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new PRFrequnecyDTO(repoName, GitHubNaming.setUserName(user), repoPRCount, userPRCount, Calculate.density(userPRCount, repoPRCount));
    }

    private GitOrganization setOrganization(String org) {
        return new GitOrganization(org);
    }

    private GHRepository setRepo(String org, String repoName) {
        return setOrganization(org).getRepository(repoName);
    }

    private boolean isBetween(GHPullRequest pullRequest, long dayBeforeEpoch, long dayAfterEpoch) {
        try {
            return pullRequest.getUpdatedAt().getTime() > dayBeforeEpoch &&
                    pullRequest.getUpdatedAt().getTime() < dayAfterEpoch;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean contributorStatIsBetween(GHRepositoryStatistics.ContributorStats contributorStats,long dayBeforeEpoch, long dayAfterEpoch){
        try {
            return contributorStats.getUpdatedAt().getTime() > dayBeforeEpoch &&
                    contributorStats.getUpdatedAt().getTime() < dayAfterEpoch;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isUser(GHPullRequest pullRequest, String user) {
        try {
            return pullRequest.getUser().getLogin().equals(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //generate Key which is a Contributor username and value which is a total commit count
    private Map<String,Integer> setContribution(List<GHRepositoryStatistics.ContributorStats> contributorStats){
        Map<GHUser, Integer> contributorsInfo = contributorStats.stream()
                .collect(Collectors.
                        toMap(GHRepositoryStatistics.ContributorStats::getAuthor, GHRepositoryStatistics.ContributorStats::getTotal));
        Map<String, Integer> finalContrInfo = new HashMap<>();
        contributorsInfo.forEach((key, value) -> finalContrInfo.put(key.getLogin(), value));
        return finalContrInfo;
    }
}
