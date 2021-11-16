package com.company.statisticsservice.util;

import com.company.statisticsservice.dto.RequestDto;
import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RestRequest extends RestTemplate {

    private RestTemplate restTemplate = new RestTemplate();
    private static RestRequest restRequest;

    public static RestRequest getRestRequest() {
        if (restRequest == null) {
            restRequest = new RestRequest();
        }
        return restRequest;
    }

    public GitAccessResponseDto requestGitAccess(RequestDto requestToGitAccess) {

        //TODO exchange GitUserDToResponse to Optional
        GitAccessResponseDto gitUserDtoResponse = null;

        gitUserDtoResponse = restTemplate.postForObject(Constants.GITACCESSURL
                , requestToGitAccess,GitAccessResponseDto.class);

        assert gitUserDtoResponse != null;
        return gitUserDtoResponse;
    }
}
