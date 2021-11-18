package com.company.gitaccessservice.service.commit;

import com.company.gitaccessservice.dto.commit.GitAccessCommitDetailsDto;
import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.model.GitUser;
import com.company.gitaccessservice.util.GetCommit;
import com.company.gitaccessservice.util.GitHubNaming;
import com.company.gitaccessservice.util.costant.Calculate;
import com.company.gitaccessservice.util.costant.TimeStaps;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class DensityServiceImpl implements DensityService {

    private int repoCommitCount;
    private int userCommitCount;

    // This Service to serve GitUser Commit Density by special Dates
    // For get this information, methods must have GitUser and Organization Logins and Repo name
    // First step connect to GitHub by org.kohsuke.github library and getting Repo
    // Second step create query to search and return list of commits by gitHub user
    // End the end calculate density user commits by repos commits

    // Return Commit Density by special dates
    @Override
    public GitAccessCommitDetailsDto getCommitDensityByOrgRepo(RequestDto request) {

        String repoName = request.getRepoName();
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        String user = request.getlogin();
        GHRepository repo = GetCommit.setOrganization(request.getOrganization()).getRepository(repoName);

        repoCommitCount = GetCommit.getRepoCommitCount(repo, startDate, endDate);
        List<GHCommit> userCommits = GetCommit.getUserCommitCount(repo, startDate, endDate, user);
        userCommitCount = userCommits.size();

        return new GitAccessCommitDetailsDto(GitHubNaming.setUserName(user), user, repoName, userCommitCount, repoCommitCount,
                Calculate.density(userCommitCount, repoCommitCount));
    }

    @Override
    public GitAccessCommitDetailsDto getCommitDensityByOrg(RequestDto request) {
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        String user = request.getlogin();
        String org = request.getOrganization();

        Map<String, GHRepository> organization = GetCommit.setOrganization(org).getRepositories();
        for (GHRepository repo : organization.values()) {
            repoCommitCount += GetCommit.getRepoCommitCount(repo, startDate, endDate);
        }
        List<GHCommit> userCommits = new ArrayList<>();
        for (GHRepository repo : organization.values()) {
            userCommits.addAll(GetCommit.getUserCommitCount(repo, startDate, endDate, user));
        }
        userCommitCount = userCommits.size();

        return new GitAccessCommitDetailsDto(GitHubNaming.setUserName(user), user, userCommitCount, repoCommitCount,
                Calculate.density(userCommitCount, repoCommitCount));
    }

    @Override
    public GitAccessCommitDetailsDto getCommitDensityByUserRepo(RequestDto request) {
        String repoName = request.getRepoName();
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        String login = request.getlogin();
        GHRepository repo = GitUser.setGitUser(login).getRepository(repoName);

        repoCommitCount = GetCommit.getRepoCommitCount(repo, startDate, endDate);
        List<GHCommit> userCommits = GetCommit.getUserCommitCount(repo, startDate, endDate, login);
        userCommitCount = userCommits.size();

        return new GitAccessCommitDetailsDto(GitHubNaming.setUserName(login), login, repoName, userCommitCount, repoCommitCount,
                Calculate.density(userCommitCount, repoCommitCount));
    }

    @Override
    public GitAccessCommitDetailsDto getCommitDensityByUser(RequestDto request) {
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        String login = request.getlogin();


        Map<String, GHRepository> userRepos = GitUser.setGitUser(login).setRepos();
        for (GHRepository repo : userRepos.values()) {
            repoCommitCount += GetCommit.getRepoCommitCount(repo, startDate, endDate);
        }
        List<GHCommit> userCommits = new ArrayList<>();
        for (GHRepository repo : userRepos.values()) {
            userCommits.addAll(GetCommit.getUserCommitCount(repo, startDate, endDate, login));
        }
        userCommitCount = userCommits.size();

        return new GitAccessCommitDetailsDto(GitHubNaming.setUserName(login), login, userCommitCount, repoCommitCount,
                Calculate.density(userCommitCount, repoCommitCount));
    }
}

