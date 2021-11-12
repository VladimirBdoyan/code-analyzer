package com.company.statisticsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "organization")
public class GitOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_id_seq")
    @SequenceGenerator(name = "organization_id_seq", sequenceName = "organization_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gitOrganization", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GitUser> gitUsers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "organization", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GitRepository> repositories;

    public GitOrganization() {

    }

    public GitOrganization(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<GitUser> getGitUsers() {
        return gitUsers;
    }

    public void setGitUsers(List<GitUser> gitUsers) {
        this.gitUsers = gitUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitOrganization that = (GitOrganization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
