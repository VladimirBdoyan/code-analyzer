package com.company.gitaccessservice.service;

import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PullRequestInfo {

    GHCommitQueryBuilder queryBuilder;

    public List<GHPullRequestCommitDetail> getCommits(GHPullRequest pullRequest ) {

        // Find all Commits in Pull Request
        try {
           return  pullRequest.listCommits().toList();
//            queryBuilder = repository.queryCommits()
//                    .from(pullRequest.getMergeCommitSha());
//            return queryBuilder.list().toList();

        } catch (IOException e) {
            System.out.println("Can not get commits PullRequest : " + pullRequest.getTitle());
            throw new RuntimeException(); // TODO change by own created exception;
        }
    }

    // Find Issue Messages in Pull Request
    public Integer getPullRequestConversationCount(GHPullRequest pullRequest){
        try {
            return  pullRequest.getComments().stream()
                    .filter(ghIssueComment -> isIssueMessage(ghIssueComment.getBody()))
                    .collect(Collectors.toList()).size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public boolean isIssueMessage(String message) {

        return message.contains("fix") || message.contains("bug")
                || message.contains("error") || message.contains("problem");
    }

}
