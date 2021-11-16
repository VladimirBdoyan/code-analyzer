package com.company.statisticsservice.dto.gitAccessResponse;


import java.util.Objects;

public class GitAccessResponseDto {
    private Long id;

    private String userName;
    private String login;
    private String organization;
    private String repoName;
    private GitAccessCommitDetailsDto commitDetailsDto;
    private GitAccessPullRequestDetailsDto pullRequestDetailsDto;
    private Long since;
    private Long till;

//    private String userName;
//    private String login;
//    private String orgName;
//    private String repoName;
//    private Long since;
//    private Long till;
//    private Integer commitCount;
//    private Integer repoCommitCount;
//    private GitAccessCommitDetailsDto commitDetailsDto;
//    private GitAccessPullRequestDetailsDto pullRequestDetailsDto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    //    public String getOrgName() {
//        return orgName;
//    }
//
//    public void setOrgName(String orgName) {
//        this.orgName = orgName;
//    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

//    public Integer getCommitCount() {
//        return commitCount;
//    }
//
//    public void setCommitCount(Integer commitCount) {
//        this.commitCount = commitCount;
//    }
//
//    public Integer getRepoCommitCount() {
//        return repoCommitCount;
//    }
//
//    public void setRepoCommitCount(Integer repoCommitCount) {
//        this.repoCommitCount = repoCommitCount;
//    }

    public Long getSince() {
        return since;
    }

    public void setSince(Long since) {
        this.since = since;
    }

    public Long getTill() {
        return till;
    }

    public void setTill(Long till) {
        this.till = till;
    }

    public GitAccessCommitDetailsDto getCommitDetailsDto() {
        return commitDetailsDto;
    }

    public void setCommitDetailsDto(GitAccessCommitDetailsDto commitDetailsDto) {
        this.commitDetailsDto = commitDetailsDto;
    }

    public GitAccessPullRequestDetailsDto getPullRequestDetailsDto() {
        return pullRequestDetailsDto;
    }

    public void setPullRequestDetailsDto(GitAccessPullRequestDetailsDto pullRequestDetailsDto) {
        this.pullRequestDetailsDto = pullRequestDetailsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitAccessResponseDto that = (GitAccessResponseDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GitUserDto{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
