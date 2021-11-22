package com.company.gitaccessservice.util;

import com.company.gitaccessservice.model.GitUser;

import java.io.IOException;

public class GitHubNaming {
    public static String setUserName(String user) {
        GitUser gitUser = GitUser.setGitUser(user);
        String name;
        try {
            name = gitUser.getUser().getName();
            return name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
