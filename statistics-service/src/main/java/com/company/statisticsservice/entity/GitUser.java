package com.company.statisticsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "githubuser")
public class GitUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;


    @ManyToOne()
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "organization_id")
    private GitOrganization gitOrganization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gitUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PullRequestReport> pullRequest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gitUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GitRepository> repositories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userName", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommitDensity> commitReport;

    public GitUser() {
    }

    public GitUser(String login) {
        this.login = login;
    }

    public GitUser(Long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public GitOrganization getGitOrganization() {
        return gitOrganization;
    }

    public void setGitOrganization(GitOrganization gitOrganization) {
        this.gitOrganization = gitOrganization;
    }

    public List<PullRequestReport> getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(List<PullRequestReport> pullRequest) {
        this.pullRequest = pullRequest;
    }

    public List<GitRepository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<GitRepository> repositories) {
        this.repositories = repositories;
    }

    public List<CommitDensity> getCommitReport() {
        return commitReport;
    }

    public void setCommitReport(List<CommitDensity> commitReport) {
        this.commitReport = commitReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitUser gitUser = (GitUser) o;
        return id.equals(gitUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
