package com.company.gitaccessservice.dto.mapper;

import com.company.gitaccessservice.dto.commit.CommitDTO;
import org.kohsuke.github.GHCommit;

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
}
