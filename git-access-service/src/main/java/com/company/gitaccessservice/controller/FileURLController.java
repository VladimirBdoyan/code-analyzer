package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.service.FileURLServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api/v1/git-access/URL")
public class FileURLController {

    private final FileURLServiceImpl fileURLService;

    public FileURLController(FileURLServiceImpl fileURLService) {
        this.fileURLService = fileURLService;
    }

    @GetMapping()
    public List<URL> getDeveloperCommitURL(@RequestBody RequestDto urlDto) {
        String repo = null;
        String org = null;
        try {
            repo = urlDto.getRepoName();
            org = urlDto.getOrganization();
        } catch (NullPointerException e) {
            repo = null;
            org = null;
        }


        if (repo == null && org == null) {
            return fileURLService.getFileURLbyUser(urlDto);
        } else if (repo != null && org == null) {
            return fileURLService.getFileURLbyUserRepo(urlDto);
        } else if (repo == null && org != null) {
            return fileURLService.getFileURLbyOrg(urlDto);
        } else {
            return fileURLService.getFileURLbyOrgRepo(urlDto);
        }
    }
}
