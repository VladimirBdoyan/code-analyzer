package com.company.statisticsservice.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pullrequest_report", schema = "statistics")
public class PullRequestReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pullrequest_id_seq")
    @SequenceGenerator(name = "pullrequest_id_seq", sequenceName = "pullrequest_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    private GitUser gitUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repo_id")
    private GitRepository gitRepository;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private GitOrganization organization;

    @Column(name = "conversation_count")
    private int ConversationCount;

    @Column(name = "commit_comment_count")
    private int pullRequestCommitCommentCount;

    @Column(name = "density")
    private double pullRequestDensity;

    @Column(name = "commit_density")
    private double pullRequestCommitDensity;

    @Column(name ="start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name ="end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "report_create_duration")
    private Long durationInSeconds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GitUser getGitUser() {
        return gitUser;
    }

    public void setGitUser(GitUser gitUser) {
        this.gitUser = gitUser;
    }

    public GitRepository getGitRepository() {
        return gitRepository;
    }

    public void setGitRepository(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
    }

    public GitOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(GitOrganization organization) {
        this.organization = organization;
    }

    public int getConversationCount() {
        return ConversationCount;
    }

    public void setConversationCount(int conversationCount) {
        ConversationCount = conversationCount;
    }

    public int getPullRequestCommitCommentCount() {
        return pullRequestCommitCommentCount;
    }

    public void setPullRequestCommitCommentCount(int pullRequestCommitCommentCount) {
        this.pullRequestCommitCommentCount = pullRequestCommitCommentCount;
    }

    public double getPullRequestDensity() {
        return pullRequestDensity;
    }

    public void setPullRequestDensity(double pullRequestDensity) {
        this.pullRequestDensity = pullRequestDensity;
    }

    public double getPullRequestCommitDensity() {
        return pullRequestCommitDensity;
    }

    public void setPullRequestCommitDensity(double pullRequestCommitDensity) {
        this.pullRequestCommitDensity = pullRequestCommitDensity;
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

    public Long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    @Override
    public String toString() {
        return "GitUserPullRequestsReport{" +
                "id=" + id +
                ", gitUser=" + gitUser +
                ", gitRepository=" + gitRepository +
                ", ConversationCount=" + ConversationCount +
                ", pullRequestCommitCommentCount=" + pullRequestCommitCommentCount +
                ", pullRequestDensity=" + pullRequestDensity +
                ", pullRequestCommitDensity=" + pullRequestCommitDensity +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", durationInSeconds=" + durationInSeconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PullRequestReport that = (PullRequestReport) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
