package com.company.gitaccessservice.service;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitQueryBuilder;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestCommitDetail;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PullRequestInfo {

    private int prCommentCount;

    // Getting Author Name from Repo PR , this is first commit Creator
    public String getAuthorName(GHPullRequest pullRequest) {
        String name = null;
        try {
            List<GHPullRequestCommitDetail> commits = pullRequest.listCommits().toList();
            name = commits.get(0).getCommit().getAuthor().getName();
            return name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public List<GHCommit> getCommitByAuthor(GHPullRequest pullRequest, String author) {
        GHCommitQueryBuilder queryBuilder;
        List<GHCommit> prCommitsByAuthor = new ArrayList<>();

        // Find all Commits in Pull Request by PR Author
        try {
            queryBuilder = pullRequest.getRepository().queryCommits().from(pullRequest.getMergeCommitSha()).author(author);
            prCommitsByAuthor = queryBuilder.list().toList();
            prCommentCount = pullRequest.getCommentsCount();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prCommitsByAuthor;
    }


    public int getPRCommentCount() {
        return prCommentCount;
    }

}
