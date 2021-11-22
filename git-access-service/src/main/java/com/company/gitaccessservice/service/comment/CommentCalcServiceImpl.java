package com.company.gitaccessservice.service.comment;

import com.company.gitaccessservice.dto.commit.CommitDTO;
import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.dto.mapper.CommitDTOMapper;
import com.company.gitaccessservice.dto.pullRequest.GitAccessPullRequestDetailsDto;
import com.company.gitaccessservice.dto.pullRequest.PullRequestDto;
import com.company.gitaccessservice.model.GitOrganization;
import com.company.gitaccessservice.model.GitUser;
import com.company.gitaccessservice.service.PullRequestInfo;
import com.company.gitaccessservice.util.GetCommit;
import com.company.gitaccessservice.util.GitHubNaming;
import com.company.gitaccessservice.util.costant.TimeStaps;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// This Service to serve GitUser PullRequest Density , Pull Request Commit Density,
// find Issue messages from Pull Request Conversation by special Dates
// For get this information, methods must have GitUser and Organization Logins and Repo name
// First step connect to GitHub by org.kohsuke.github library and getting Repo
// Second step create query to search and return list of Pull Request by gitHub user
// End the end calculate densities

@Service
public class CommentCalcServiceImpl implements CommentCalcService {
    private int pullRequestCountByGitUser;
    private int pullRequestCountByEveryOne;
    private int pullRequestConversationCount;
    private int pullRequestCommitCountByUser;
    private int pullRequestCommitCountByEveryOne;
    private int pullRequestCommitCommentCount;

    private final PullRequestInfo pullRequestInfo;

    public CommentCalcServiceImpl(PullRequestInfo pullRequestInfo) {
        this.pullRequestInfo = pullRequestInfo;
    }

    @Override
    public GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByOrgRepo(RequestDto request) {
        String repoName = request.getRepoName();
        long startDate = request.getStartDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        long endDate = request.getEndDate().getTime();
        String user = request.getlogin();
        String org = request.getOrganization();

        String userName = GitHubNaming.setUserName(user);
        GitOrganization organization = GetCommit.setOrganization(org);
        GHRepository repo = organization.getRepository(repoName);
        List<PullRequestDto> prDTOs = new ArrayList<>();
        List<GHPullRequest> pullRequests = new ArrayList<>();
        Map<String, List<GHPullRequestCommitDetail>> pullRequestCommits = new HashMap<>();

        try {
            pullRequests = organization.getPullRequests(repo, startDate, endDate);

            for (GHPullRequest pullRequest : pullRequests) {

                pullRequestCommits.put(pullRequest.getTitle(), pullRequestInfo.getCommits(pullRequest));

                String title = pullRequest.getTitle();
                if (pullRequest.getUser().getLogin().equals(user)) {
                    prDTOs.add(new PullRequestDto(title
                            , pullRequestInfo.getPullRequestConversationCount(pullRequest)
                            , CommitDTOMapper.mapToDTOs1(getCommitByAuthor(pullRequestCommits
                            .get(pullRequest.getTitle()), userName))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        pullRequestCountByGitUser = prDTOs.size();
        pullRequestCountByEveryOne = pullRequests.size();
        pullRequestConversationCount = prDTOs.stream()
                .mapToInt(PullRequestDto::getPullRequestConversationCount)
                .sum();
        pullRequestCommitCountByUser = prDTOs.stream()
                .mapToInt(pullRequestDto -> pullRequestDto.getCommits().size())
                .sum();
        pullRequestCommitCountByEveryOne = pullRequestCommits.values()
                .stream().mapToInt(List::size)
                .sum();
        pullRequestCommitCommentCount = prDTOs.stream()
                .mapToInt(pullRequestDto -> (pullRequestDto.getCommits()
                        .stream().mapToInt(CommitDTO::getCommentCount))
                        .sum()).sum();
        return new GitAccessPullRequestDetailsDto(org,repoName
                , userName
                , pullRequestCountByGitUser
                , pullRequestCountByEveryOne
                , pullRequestConversationCount
                , pullRequestCommitCountByUser
                , pullRequestCommitCountByEveryOne
                , pullRequestCommitCommentCount);
    }

    @Override
    public GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByOrg(RequestDto request) {
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        String user = request.getlogin();
        String org = request.getOrganization();

        String userName = GitHubNaming.setUserName(user);
        GitOrganization organization = GetCommit.setOrganization(org);
        List<PullRequestDto> prDTOs = new ArrayList<>();
        List<GHPullRequest> pullRequests = new ArrayList<>();
        Map<String, List<GHPullRequestCommitDetail>> pullRequestCommits = new HashMap<>();
        Map<String,GHRepository> repos = organization.getRepositories();

        try {
            for(GHRepository repo: repos.values()) {
                pullRequests.addAll(organization.getPullRequests(repo, startDate, endDate));
            }
            for (GHPullRequest pullRequest : pullRequests) {

                pullRequestCommits.put(pullRequest.getTitle(), pullRequestInfo.getCommits(pullRequest));

                String title = pullRequest.getTitle();
                if (pullRequest.getUser().getLogin().equals(user)) {
                    prDTOs.add(new PullRequestDto(title
                            , pullRequestInfo.getPullRequestConversationCount(pullRequest)
                            , CommitDTOMapper.mapToDTOs1(getCommitByAuthor(pullRequestCommits
                            .get(pullRequest.getTitle()), userName))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        pullRequestCountByGitUser = prDTOs.size();
        pullRequestCountByEveryOne = pullRequests.size();
        pullRequestConversationCount = prDTOs.stream()
                .mapToInt(PullRequestDto::getPullRequestConversationCount)
                .sum();
        pullRequestCommitCountByUser = prDTOs.stream()
                .mapToInt(pullRequestDto -> pullRequestDto.getCommits().size())
                .sum();
        pullRequestCommitCountByEveryOne = pullRequestCommits.values()
                .stream().mapToInt(List::size)
                .sum();
        pullRequestCommitCommentCount = prDTOs.stream()
                .mapToInt(pullRequestDto -> (pullRequestDto.getCommits()
                        .stream().mapToInt(CommitDTO::getCommentCount))
                        .sum()).sum();
        return new GitAccessPullRequestDetailsDto(org
                , userName
                , pullRequestCountByGitUser
                , pullRequestCountByEveryOne
                , pullRequestConversationCount
                , pullRequestCommitCountByUser
                , pullRequestCommitCountByEveryOne
                , pullRequestCommitCommentCount);
    }

    @Override
    public GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByUser(RequestDto request) {
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        String login = request.getlogin();

        String userName = GitHubNaming.setUserName(login);

        GitUser gitUser =  GitUser.setGitUser(login);

        List<PullRequestDto> prDTOs = new ArrayList<>();
        List<GHPullRequest> pullRequests = new ArrayList<>();
        Map<String, List<GHPullRequestCommitDetail>> pullRequestCommits = new HashMap<>();
        Map<String,GHRepository> repos = gitUser.setRepos();

        try {
            for(GHRepository repo: repos.values()) {
                pullRequests.addAll(gitUser.getPullRequests(repo, startDate, endDate));
            }
            for (GHPullRequest pullRequest : pullRequests) {

                pullRequestCommits.put(pullRequest.getTitle(), pullRequestInfo.getCommits(pullRequest));

                String title = pullRequest.getTitle();
                if (pullRequest.getUser().getLogin().equals(login)) {
                    prDTOs.add(new PullRequestDto(title
                            , pullRequestInfo.getPullRequestConversationCount(pullRequest)
                            , CommitDTOMapper.mapToDTOs1(getCommitByAuthor(pullRequestCommits
                            .get(pullRequest.getTitle()), userName))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        pullRequestCountByGitUser = prDTOs.size();
        pullRequestCountByEveryOne = pullRequests.size();
        pullRequestConversationCount = prDTOs.stream()
                .mapToInt(PullRequestDto::getPullRequestConversationCount)
                .sum();
        pullRequestCommitCountByUser = prDTOs.stream()
                .mapToInt(pullRequestDto -> pullRequestDto.getCommits().size())
                .sum();
        pullRequestCommitCountByEveryOne = pullRequestCommits.values()
                .stream().mapToInt(List::size)
                .sum();
        pullRequestCommitCommentCount = prDTOs.stream()
                .mapToInt(pullRequestDto -> (pullRequestDto.getCommits()
                        .stream().mapToInt(CommitDTO::getCommentCount))
                        .sum()).sum();
        return new GitAccessPullRequestDetailsDto(userName
                , pullRequestCountByGitUser
                , pullRequestCountByEveryOne
                , pullRequestConversationCount
                , pullRequestCommitCountByUser
                , pullRequestCommitCountByEveryOne
                , pullRequestCommitCommentCount);
    }

    @Override
    public GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByUserRepo(RequestDto request) {
        String repoName = request.getRepoName();
        long startDate = request.getStartDate().getTime() + TimeStaps.TIMSTEPOFDAY;
        long endDate = request.getEndDate().getTime();
        String login = request.getlogin();

        String userName = GitHubNaming.setUserName(login);
        GitUser gitUser = GitUser.setGitUser(login);
        GHRepository repo = gitUser.getRepository(repoName);
        List<PullRequestDto> prDTOs = new ArrayList<>();
        List<GHPullRequest> pullRequests = new ArrayList<>();
        Map<String, List<GHPullRequestCommitDetail>> pullRequestCommits = new HashMap<>();

        try {
            pullRequests = gitUser.getPullRequests(repo, startDate, endDate);

            for (GHPullRequest pullRequest : pullRequests) {

                pullRequestCommits.put(pullRequest.getTitle(), pullRequestInfo.getCommits(pullRequest));

                String title = pullRequest.getTitle();
                if (pullRequest.getUser().getLogin().equals(login)) {
                    prDTOs.add(new PullRequestDto(title
                            , pullRequestInfo.getPullRequestConversationCount(pullRequest)
                            , CommitDTOMapper.mapToDTOs1(getCommitByAuthor(pullRequestCommits
                            .get(pullRequest.getTitle()), userName))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        pullRequestCountByGitUser = prDTOs.size();
        pullRequestCountByEveryOne = pullRequests.size();
        pullRequestConversationCount = prDTOs.stream()
                .mapToInt(PullRequestDto::getPullRequestConversationCount)
                .sum();
        pullRequestCommitCountByUser = prDTOs.stream()
                .mapToInt(pullRequestDto -> pullRequestDto.getCommits().size())
                .sum();
        pullRequestCommitCountByEveryOne = pullRequestCommits.values()
                .stream().mapToInt(List::size)
                .sum();
        pullRequestCommitCommentCount = prDTOs.stream()
                .mapToInt(pullRequestDto -> (pullRequestDto.getCommits()
                        .stream().mapToInt(CommitDTO::getCommentCount))
                        .sum()).sum();
        return new GitAccessPullRequestDetailsDto(pullRequestCountByGitUser
                ,repoName
                , userName
                , pullRequestCountByEveryOne
                , pullRequestConversationCount
                , pullRequestCommitCountByUser
                , pullRequestCommitCountByEveryOne
                , pullRequestCommitCommentCount);
    }

    // Find all Commits by PR Author
    private List<GHPullRequestCommitDetail> getCommitByAuthor(List<GHPullRequestCommitDetail> commits, String author) {

        return commits.stream()
                .filter(ghCommit -> isAuthor(ghCommit, author))
                .collect(Collectors.toList());

    }

    private boolean isAuthor(GHPullRequestCommitDetail ghCommit, String author) {
        return ghCommit.getCommit().getAuthor().getName().equals(author);
    }
}
