package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.dto.pullRequest.GitAccessPullRequestDetailsDto;
import com.company.gitaccessservice.service.comment.CommentCalcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codeAnalyzer/comment")
public class CommentController {

    private final CommentCalcService commentCalcService;

    public CommentController(CommentCalcService commentCalcService) {
        this.commentCalcService = commentCalcService;
    }

    @GetMapping()
    public GitAccessPullRequestDetailsDto getPullRequestCommentCount(RequestDto request) {

        return commentCalcService.creatGitUserPullRequestDetailsByOrgRepo(request);
    }
}
