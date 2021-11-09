package com.company.statisticsservice.controler;


import com.company.statisticsservice.Service.GitUserServiceImpl;
import com.company.statisticsservice.dto.GitUserDto;
import com.company.statisticsservice.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {


    private final GitUserServiceImpl gitUserService;

    public StatisticsController(GitUserServiceImpl gitUserService) {
        this.gitUserService = gitUserService;
    }


    @GetMapping("/{org}/{repo}/{user}/total")
    public ResponseEntity<GitUserDto> getPRDensityTotal(@PathVariable String org, @PathVariable String repo,
                                                        @PathVariable String user) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder url = new StringBuilder();

        //TODO exchange GitUserDToResponse to Optional
        GitUserDto gitUserDtoResponse = null;
        url.append(Constants.GITACCESSURL).append("/")
                .append(org).append("/")
                .append(repo).append("/")
                .append(user).append("/total");
        try {
            gitUserDtoResponse = restTemplate.getForObject(new URI(url.toString()), GitUserDto.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert gitUserDtoResponse != null;
        gitUserDtoResponse.setLogin(user);
        return ResponseEntity.of(gitUserService.create(gitUserDtoResponse));
    }
}
