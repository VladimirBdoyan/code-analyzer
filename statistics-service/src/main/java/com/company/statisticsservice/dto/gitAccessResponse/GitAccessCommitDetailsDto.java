package com.company.statisticsservice.dto.gitAccessResponse;

import java.util.Objects;

public class GitAccessCommitDetailsDto {
    private Long id;

    private String userName;
    private String login;
    private String repoName;
    private Integer userCommitCount;
    private Integer repoCommitCount;
    private Long since;
    private Long till;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public Integer getCommitCount() {
        return userCommitCount;
    }

    public void setCommitCount(Integer userCommitCount) {
        this.userCommitCount = userCommitCount;
    }

    public Integer getRepoCommitCount() {
        return repoCommitCount;
    }

    public void setRepoCommitCount(Integer repoCommitCount) {
        this.repoCommitCount = repoCommitCount;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitAccessCommitDetailsDto that = (GitAccessCommitDetailsDto) o;
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
