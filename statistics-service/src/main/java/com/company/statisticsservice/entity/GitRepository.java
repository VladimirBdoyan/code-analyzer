package com.company.statisticsservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "githubrepository")
public class GitRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "repository_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "repository_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private GitOrganization organization;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private GitUser gitUser;

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
}
