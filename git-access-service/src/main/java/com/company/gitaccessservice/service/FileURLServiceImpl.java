package com.company.gitaccessservice.service;

import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.util.GetCommit;
import com.company.gitaccessservice.util.GitRepoDownload;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class FileURLServiceImpl {

    public List<URL> getFileURLbyRepo(RequestDto request){
        String repoName = request.getRepoName();
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime();
        String user = request.getUsername();
        GHRepository repo = GetCommit.setOrganization(request.getOrganization()).getRepository(repoName);

        List<GHCommit> commits = GetCommit.getUserCommitCount(repo,startDate,endDate,user);
        return GitRepoDownload.getUserCommitURL(commits);
    }


    public List<URL> getFileURLbyOrg(RequestDto request){
        String repoName = request.getRepoName();
        long startDate = request.getStartDate().getTime();
        long endDate = request.getEndDate().getTime();
        String user = request.getUsername();

        Map<String,GHRepository> orgRepos = GetCommit.setOrganization(request.getOrganization()).getRepositories();
        List<GHCommit> commits = new ArrayList<>();
        for(GHRepository repo :orgRepos.values()){
            commits.addAll(GetCommit.getUserCommitCount(repo,startDate,endDate,user));
        }
        return GitRepoDownload.getUserCommitURL(commits);
    }
}
