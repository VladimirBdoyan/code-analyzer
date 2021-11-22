package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.URLResponseDTO;
import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.service.FileURLServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api/v1/git-access/URL")
public class FileURLController {

    private final FileURLServiceImpl fileURLService;

    public FileURLController(FileURLServiceImpl fileURLService) {
        this.fileURLService = fileURLService;
    }

    @PostMapping()
    public URLResponseDTO getDeveloperCommitURL(@RequestBody RequestDto urlDto) {
        String repo = null;
        String org = null;
        try {
            repo = urlDto.getRepoName();
            org = urlDto.getOrganization();
        } catch (NullPointerException e) {
            repo = null;
            org = null;
        }
        URLResponseDTO responseDTO = new URLResponseDTO();


        if (repo == null && org == null) {
            responseDTO.setUrls(fileURLService.getFileURLbyUser(urlDto));
            return responseDTO;
        } else if (repo != null && org == null) {
            responseDTO.setUrls(fileURLService.getFileURLbyUserRepo(urlDto));
            return responseDTO;
        } else if (repo == null && org != null) {
            responseDTO.setUrls(fileURLService.getFileURLbyOrg(urlDto));
            return responseDTO;
        } else {
            responseDTO.setUrls(fileURLService.getFileURLbyOrgRepo(urlDto));
            return responseDTO ;
        }
    }
}
