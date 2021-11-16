package com.company.gitaccessservice.dto.commit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitAccessCommitDetailsDto {
    private String userName;
    private String login;
    private String repoName;
    private Integer userCommitCount;
    private Integer repoCommitCount;
    private double userCommitDensity;


    public GitAccessCommitDetailsDto(String userName, String login, String repoName, Integer userCommitCount, Integer repoCommitCount,
                                     double userCommitDensity) {
        this.userName = userName;
        this.login = login;
        this.repoName = repoName;
        this.userCommitCount = userCommitCount;
        this.repoCommitCount = repoCommitCount;
        this.userCommitDensity = userCommitDensity;
    }
    public GitAccessCommitDetailsDto(String userName, String login, Integer userCommitCount, Integer repoCommitCount,
                                     double userCommitDensity) {
        this.userName = userName;
        this.login = login;
        this.userCommitCount = userCommitCount;
        this.repoCommitCount = repoCommitCount;
        this.userCommitDensity = userCommitDensity;
    }


    public double getUserCommitDensity() {
        return userCommitDensity;
    }

    public void setUserCommitDensity(double userCommitDensity) {
        this.userCommitDensity = userCommitDensity;
    }

    @JsonProperty("name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("repoName")
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    @JsonProperty("commitCount")
    public Integer getUserCommitCount() {
        return userCommitCount;
    }

    public void setUserCommitCount(Integer userCommitCount) {
        this.userCommitCount = userCommitCount;
    }

    @JsonProperty("repoCommitCount")
    public Integer getRepoCommitCount() {
        return repoCommitCount;
    }

    public void setRepoCommitCount(Integer repoCommitCount) {
        this.repoCommitCount = repoCommitCount;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
