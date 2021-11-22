package com.company.statisticsservice.entity;


import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "commit_density_report", schema = "statistics")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private GitOrganization organization;

    private double density;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public GitOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(GitOrganization organization) {
        this.organization = organization;
    }
}
