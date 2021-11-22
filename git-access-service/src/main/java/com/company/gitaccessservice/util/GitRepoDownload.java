package com.company.gitaccessservice.util;

import org.apache.commons.io.IOUtils;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitQueryBuilder;
import org.kohsuke.github.GHRepository;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GitRepoDownload {
    public static String download(GHRepository repo) {

        File outputFileName = new File("C:\\Users\\VLAD\\Desktop\\" + repo.getName() + ".zip");
        try {
            repo.readZip((InputStream inputstream) -> {
                IOUtils.copy(new ByteArrayInputStream(IOUtils.toByteArray(inputstream)),
                        new FileOutputStream(outputFileName));
                return outputFileName.getName();
            }, null);
        } catch (
                IOException e) {
            e.printStackTrace();

        }
        return outputFileName.getName();
    }
    //Return the Commit content URLS by Author
    public static List<URL> getUserCommitURL (List<GHCommit> userCommits){
        List<URL> contentUrl = new ArrayList<>();
        List<GHCommit.File> files = new ArrayList<>();
        for (GHCommit commit : userCommits) {
            try {
                files.addAll(commit.getFiles());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (GHCommit.File file : files) {
            if(file.getRawUrl().getPath().contains("java")) {
                contentUrl.add(file.getRawUrl());
            }
        }
        return contentUrl;
    }
}

