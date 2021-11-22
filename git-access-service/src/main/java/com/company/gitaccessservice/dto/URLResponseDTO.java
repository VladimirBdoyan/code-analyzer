package com.company.gitaccessservice.dto;

import java.net.URL;
import java.util.List;

public class URLResponseDTO {
    private String userName;
    private List<URL> urls;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }
}

