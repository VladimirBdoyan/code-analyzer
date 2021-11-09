package com.company.gitaccessservice.service;

import org.kohsuke.github.GHCommit;

import java.util.List;
import java.util.Map;

public interface GitAccess {
    Map<String, List<GHCommit>> getReposCommits(RepositoryInfo repositoryInfo);

}
