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
        if (urlDto.getRepoName() == null) {
            return fileURLService.getFileURLbyOrg(urlDto);
        } else {
            return fileURLService.getFileURLbyRepo(urlDto);
        }
    }
}
