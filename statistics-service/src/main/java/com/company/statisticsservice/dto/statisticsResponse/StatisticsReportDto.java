package com.company.statisticsservice.dto.statisticsResponse;

import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.entity.PullRequestReport;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

//@NamedNativeQuery(
//        name = "aaaa",
//        query = "SELECT gu.name AS username," +
//            " gr.name AS repoName, cf.density, " +
//            "cf.start_date, cf.end_date from commitfrequency cf" +
//            " inner join githubrepository gr on gr.id = cf.repo_id" +
//            " inner join githubuser gu on gu.id = cf.user_id where cf.user_id IN :userId ",
//        resultSetMapping = "commitReportsMapping")
//@SqlResultSetMapping(name = "commitReportsMapping",
//        classes = {@ConstructorResult(targetClass = CommitDensityReportDto.class,
//                columns = {@ColumnResult(name = "userName", type = String.class),
//                        @ColumnResult(name = "repoName", type = String.class),
//                        @ColumnResult(name = "density", type = Double.class),
//                        @ColumnResult(name = "starDate", type = Date.class),
//                        @ColumnResult(name = "endDate", type = Date.class)})})

public class StatisticsReportDto {
    private String orgName;
    private String login;
    private String userName;
    private String repoName;
    private String sinceDate;
    private String tillDate;
    private double density;
    private Date startDate;
    private Date endDate;

    private CommitDensityDto commitDensityReport;

    private PullRequestReportDto pullRequestReport;

    public StatisticsReportDto() {
    }

    public StatisticsReportDto(String orgName, String userName, String repoName, double density,
                               Date startDate, Date endDate) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        this.orgName = orgName;
        this.userName = userName;
        this.repoName = repoName;
        this.density = density;
        this.sinceDate = format.format(startDate);
        this.tillDate = format.format(endDate);
    }
    public StatisticsReportDto(String orgName, String userName, Date startDate, Date endDate,
                               CommitDensityDto commitDensityReport,
                               PullRequestReportDto pullRequestReport) {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        this.orgName = orgName;
        this.userName = userName;
        this.sinceDate = format.format(startDate);
        this.tillDate = format.format(endDate);
        this.commitDensityReport = commitDensityReport;
        this.pullRequestReport = pullRequestReport;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin(){return login;}

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
    @JsonIgnore
    public double getDensity() {
        return density;
    }
    @JsonIgnore
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
    @JsonIgnore
    public String getSinceDate() {
        return sinceDate;
    }
    @JsonIgnore
    public void setSinceDate(String sinceDate) {
        this.sinceDate = sinceDate;
    }
    @JsonIgnore
    public String getTillDate() {
        return tillDate;
    }
    @JsonIgnore
    public void setTillDate(String tillDate) {
        this.tillDate = tillDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public CommitDensityDto getCommitDensityReport() {
        return commitDensityReport;
    }

    public void setCommitDensityReportDto(CommitDensityDto commitDensityReport) {
        this.commitDensityReport = commitDensityReport;
    }

    public PullRequestReportDto getPullRequestReportDto() {
        return pullRequestReport;
    }

    public void setPullRequestReportDto(PullRequestReportDto pullRequestReport) {
        this.pullRequestReport = pullRequestReport;
    }
}
