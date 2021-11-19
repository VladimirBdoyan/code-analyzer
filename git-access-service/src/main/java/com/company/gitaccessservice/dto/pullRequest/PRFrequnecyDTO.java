package com.company.gitaccessservice.dto.pullRequest;

public class PRFrequnecyDTO {
    private String repoName;
    private String userName;
    private int repoPRCount;
    private int userPRCount;
    private double userPRDensity;

    public PRFrequnecyDTO(String repoName, String userName, int repoPRCount, int userPRCount, double userPRDensity) {
        this.repoName = repoName;
        this.userName = userName;
        this.repoPRCount = repoPRCount;
        this.userPRCount = userPRCount;
        this.userPRDensity = userPRDensity;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRepoPRCount() {
        return repoPRCount;
    }

    public void setRepoPRCount(int repoPRCount) {
        this.repoPRCount = repoPRCount;
    }

    public int getUserPRCount() {
        return userPRCount;
    }

    public void setUserPRCount(int userPRCount) {
        this.userPRCount = userPRCount;
    }

    public double getUserPRDensity() {
        return userPRDensity;
    }

    public void setUserPRDensity(double userPRDensity) {
        this.userPRDensity = userPRDensity;
    }
}
