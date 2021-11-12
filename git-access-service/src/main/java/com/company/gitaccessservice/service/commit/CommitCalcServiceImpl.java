package com.company.gitaccessservice.service.commit;

import com.company.gitaccessservice.dto.commit.CommitFrequencyDTO;
import com.company.gitaccessservice.model.GitOrganization;
import com.company.gitaccessservice.model.GitUser;
import com.company.gitaccessservice.service.PullRequestInfo;
import com.company.gitaccessservice.service.RepositoryInfo;
import com.company.gitaccessservice.util.GitHubNaming;
import com.company.gitaccessservice.util.costant.Calculate;
import com.company.gitaccessservice.util.costant.TimeSteps;
import org.kohsuke.github.GHCommitQueryBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositoryStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;


@Service
public class CommitCalcServiceImpl {

    private final PullRequestInfo pullRequestInfo;

    @Autowired
    public CommitCalcServiceImpl(PullRequestInfo pullRequestInfo) {
        this.pullRequestInfo = pullRequestInfo;
    }

    private int repoCommitCount;
    private int userCommitCount;

    // This Service to serve GitUser Commit frequency by day,week and total it'll can changed by special dates
    // For get this information, methods must have GitUser and Organization Logins and Repo name
    // First step connect to GitHub by org.kohsuke.github library and getting Repo
    // Second step create query to search and return list of commits by gitHub user
    // End the end calculate density user commits by repos commits

    // Return Commit Density by special date
    public CommitFrequencyDTO getCommitDensity(String org, String repoName, String user, String startDate,String endDate) {
        LocalDate since = LocalDate.parse(startDate);
        LocalDate till = LocalDate.parse(endDate);
        long dayBeforeEpoch = since.getLong(ChronoField.EPOCH_DAY) * TimeSteps.TIMSTEPOFDAY - TimeSteps.TIMSTEPOF3HOURS;
        long dayAfterEpoch;
        if(till.equals(since)) {
            dayAfterEpoch = dayBeforeEpoch + since.atTime(23, 59).getLong(ChronoField.MILLI_OF_DAY);
        } else{
            dayAfterEpoch = till.getLong(ChronoField.EPOCH_DAY) * TimeSteps.TIMSTEPOFDAY - TimeSteps.TIMSTEPOF3HOURS;
        }
        GHRepository repo = setOrganization(org).getRepository(repoName);
        repoCommitCount = getRepoCommitCount(repo, dayBeforeEpoch, dayAfterEpoch);
        userCommitCount = getUserCommitCount(repo,dayBeforeEpoch,dayAfterEpoch,user);
        return new CommitFrequencyDTO(GitHubNaming.setUserName(user), repoName, userCommitCount, repoCommitCount,
                Calculate.density(userCommitCount, repoCommitCount),dayBeforeEpoch,dayAfterEpoch);
    }

    // Return Commit Density by special week , takes start date for week

//    public CommitFrequencyDTO getCommitDensityWeek(String org, String repoName, String user, String date) {
//        LocalDate since = LocalDate.parse(date);
//        long dayBeforeEpoch = since.getLong(ChronoField.EPOCH_DAY) * TimeSteps.TIMSTEPOFDAY - TimeSteps.TIMSTEPOF3HOURS;
//        long dayAfterEpoch = since.plusWeeks(1).getLong(ChronoField.EPOCH_DAY) * TimeSteps.TIMSTEPOFDAY - TimeSteps.TIMSTEPOF3HOURS;
//        GHRepository repo = setOrganization(org).getRepository(repoName);
//        repoCommitCount = getRepoCommitCount(repo, dayBeforeEpoch, dayAfterEpoch);
//        userCommitCount = getUserCommitCount(repo,dayBeforeEpoch,dayAfterEpoch,user);
//        return new CommitFrequencyDTO(GitHubNaming.setUserName(user), repoName, userCommitCount, repoCommitCount, Calculate.density(userCommitCount, repoCommitCount));
//    }

    // Return Commit Density by Total

//    public CommitFrequencyDTO getCommitDensityTotal(String org, String repoName, String user) {
//
//        GHRepository repo = setOrganization(org).getRepository(repoName);
//        repoCommitCount = getRepoCommitCount(repo, 0, 0);
//        userCommitCount = getUserCommitCount(repo,0,0,user);
//        return new CommitFrequencyDTO(GitHubNaming.setUserName(user), repoName, userCommitCount, repoCommitCount, Calculate.density(userCommitCount, repoCommitCount));
//    }

    //GitUser Commit frequency by week. Return list Weeks since first commit date its only example method

//    public List<GHRepositoryStatistics.ContributorStats.Week> getWeekFrequency(String repositoryInfo, String user) {
//        List<GHRepositoryStatistics.ContributorStats.Week> contributorStat = new ArrayList<>();
//        RepositoryInfo repo = RepositoryInfo.setRepo(user + "/" + repositoryInfo);
//        GitUser gitUser = GitUser.setGitUser(user);
//        GHRepositoryStatistics repositoryStatistics = new GHRepositoryStatistics(repo.getRepository());
//        List<GHRepositoryStatistics.ContributorStats> contributor = new ArrayList<>();
//        try {
//            contributor = repositoryStatistics.getContributorStats().toList();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (GHRepositoryStatistics.ContributorStats contributorStats : contributor) {
//            if (contributorStats.getAuthor().equals(gitUser.getUser())) {
//                contributorStat = contributorStats.getWeeks();
//                return contributorStat;
//            }
//        }
//        return contributorStat;
//    }

    private GitOrganization setOrganization(String org) {
        return new GitOrganization(org);
    }

    private int getRepoCommitCount(GHRepository repo, Long after, Long till) {
        if (after > 0 && till > 0) {
            try {
                return repo.queryCommits().since(after).until(till).list().toList().size();
            } catch (IOException e) {
                System.out.println("Can not get commit count for repository : " + repo);
                throw new RuntimeException(); // TODO change by own created exception
            }
        } else {
            try {
                return repo.queryCommits().list().toList().size();
            } catch (IOException e) {
                System.out.println("Can not get commit count for repository : " + repo);
                throw new RuntimeException(); // TODO change by own created exception
            }
        }
    }

    private int getUserCommitCount(GHRepository repo, long after, long till, String user) {
        if (after > 0 && till > 0) {
            try {
                GHCommitQueryBuilder ghCommitQueryBuilder = repo.queryCommits().since(after).until(till).author(user);
                return ghCommitQueryBuilder.list().toList().size();
            } catch (IOException e) {
                System.out.println("Can not get commit count for repository : " + repo);
                throw new RuntimeException(); // TODO change by own created exception
            }
        } else {
            try {
                GHCommitQueryBuilder ghCommitQueryBuilder = repo.queryCommits().author(user);
                return ghCommitQueryBuilder.list().toList().size();
            } catch (IOException e) {
                System.out.println("Can not get commit count for repository : " + repo);
                throw new RuntimeException(); // TODO change by own created exception
            }
        }
    }
}

