package com.company.gitaccessservice.dto.mapper;

import com.company.gitaccessservice.dto.commit.CommitDTO;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHPullRequestCommitDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommitDTOMapper {

    public static List<CommitDTO> mapToDTOs(List<GHCommit> commits) {
        List<CommitDTO> commitDTOS = new ArrayList<>();
        for(GHCommit commit: commits ){
            commitDTOS.add(mapToDTO(commit));
        }
        return commitDTOS;
    }

    public static CommitDTO mapToDTO(GHCommit commit) {
        CommitDTO commitDTO = new CommitDTO();
        try {
            commitDTO.setTitle(commit.getCommitShortInfo().getMessage());
            commitDTO.setCommentCount(commit.getCommitShortInfo().getCommentCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commitDTO;
    }

    public static List<CommitDTO> mapToDTOs1(List<GHPullRequestCommitDetail> commits) {
        List<CommitDTO> commitDTOS = new ArrayList<>();
        for(GHPullRequestCommitDetail commit: commits ){
            commitDTOS.add(mapToDTO1(commit));
        }
        return commitDTOS;
    }

    public static CommitDTO mapToDTO1(GHPullRequestCommitDetail commit) {
        CommitDTO commitDTO = new CommitDTO();

            commitDTO.setTitle(commit.getCommit().getMessage());
            commitDTO.setCommentCount(commit.getCommit().getComment_count());

        return commitDTO;
    }
}
