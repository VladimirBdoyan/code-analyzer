package com.company.statisticsservice.entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "commitfrequency")
public class CommitDensity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commit_id_seq")
    @SequenceGenerator(name = "commit_id_seq", sequenceName = "commit_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne()
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    private GitUser userName;

    @ManyToOne(fetch = FetchType.LAZY)
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "repo_id")
    private GitRepository repoName;

    private double density;

    private Timestamp startDate;

    private Timestamp endDate;

    public CommitDensity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GitUser getUserName() {
        return userName;
    }

    public void setUserName(GitUser userName) {
        this.userName = userName;
    }

    public GitRepository getRepoName() {
        return repoName;
    }

    public void setRepoName(GitRepository repoName) {
        this.repoName = repoName;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
