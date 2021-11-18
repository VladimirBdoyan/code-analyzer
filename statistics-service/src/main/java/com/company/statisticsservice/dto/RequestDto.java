package com.company.statisticsservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RequestDto {
    private String login;
    private String organization;
    private String repoName;
    private Date startDate;
    private Date endDate;
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }
    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
