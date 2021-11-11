package com.company.statisticsservice.entity;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "githubrepository")
public class GitRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "repository_id_seq")
    @SequenceGenerator(name = "repository_id_seq", sequenceName = "repository_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "organization_id")
    private GitOrganization organization;

    @ManyToOne()
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    private GitUser gitUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "repoName", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommitDensity> commitDensities;

    public GitRepository() {
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

    public GitOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(GitOrganization organization) {
        this.organization = organization;
    }

    public GitUser getGitUser() {
        return gitUser;
    }

    public void setGitUser(GitUser gitUser) {
        this.gitUser = gitUser;
    }

    public List<CommitDensity> getCommitDensities() {
        return commitDensities;
    }

    public void setCommitDensities(List<CommitDensity> commitDensities) {
        this.commitDensities = commitDensities;
    }
}
