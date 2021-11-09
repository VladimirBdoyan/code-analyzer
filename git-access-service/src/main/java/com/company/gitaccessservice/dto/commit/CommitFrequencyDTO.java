package com.company.gitaccessservice.dto.commit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitFrequencyDTO {
    String userName;
    String repoName;
    Integer userCommitCount;
    Integer repoCommitCount;
    double userCommitDensity;

    public CommitFrequencyDTO(String userName, String repoName, Integer userCommitCount, Integer repoCommitCount, double userCommitDensity) {
        this.userName = userName;
        this.repoName = repoName;
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
}
