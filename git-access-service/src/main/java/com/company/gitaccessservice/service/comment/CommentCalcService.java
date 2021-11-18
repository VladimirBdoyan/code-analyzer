package com.company.gitaccessservice.service.comment;

import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.dto.pullRequest.GitAccessPullRequestDetailsDto;

public interface CommentCalcService {

     //Get Repo Pull Request by Author that have PR Title ,
     // PR Conversation Count and List of Commit with their comment count

     GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByOrgRepo(RequestDto request);
     GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByOrg(RequestDto request);
     GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByUserRepo(RequestDto request);
     GitAccessPullRequestDetailsDto creatGitUserPullRequestDetailsByUser(RequestDto request);

}
