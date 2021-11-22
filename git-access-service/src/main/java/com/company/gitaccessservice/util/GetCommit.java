package com.company.gitaccessservice.util;

import com.company.gitaccessservice.model.GitOrganization;
import com.company.gitaccessservice.model.GitUser;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitQueryBuilder;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.List;

public class GetCommit {
    public static GitOrganization setOrganization(String org) {
        return new GitOrganization(org);
    }

    public static int getRepoCommitCount(GHRepository repo, Long after, Long till) {
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

    public static List<GHCommit> getUserCommitCount(GHRepository repo, long after, long till, String user) {
        if (after > 0 && till > 0) {
            try {
                GHCommitQueryBuilder ghCommitQueryBuilder = repo.queryCommits().author(user).since(after).until(till);
                return ghCommitQueryBuilder.list().toList();
            } catch (IOException e) {
                System.out.println("Can not get commit count for repository : " + repo);
                throw new RuntimeException(); // TODO change by own created exception
            }
        } else {
            try {
                GHCommitQueryBuilder ghCommitQueryBuilder = repo.queryCommits().author(user);
                return ghCommitQueryBuilder.list().toList();
            } catch (IOException e) {
                System.out.println("Can not get commit count for repository : " + repo);
                throw new RuntimeException(); // TODO change by own created exception
            }
        }
    }
}
