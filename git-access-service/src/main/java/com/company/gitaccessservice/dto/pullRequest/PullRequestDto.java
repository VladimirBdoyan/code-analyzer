package com.company.gitaccessservice.dto.pullRequest;

import com.company.gitaccessservice.dto.commit.CommitDTO;

import java.util.List;

public class PullRequestDto {

    private String prTitle;
    private Integer pullRequestConversationCount;

    private List<CommitDTO> commits;


    public PullRequestDto(String prTitle, Integer prCommentCount, List<CommitDTO> commits) {
        this.prTitle = prTitle;
        this.pullRequestConversationCount = prCommentCount;
        this.commits = commits;

    }

    public Integer getPullRequestConversationCount() {
        return pullRequestConversationCount;
    }


    public List<CommitDTO> getCommits() {
        return commits;
    }
}
