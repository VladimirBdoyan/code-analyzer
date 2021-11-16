package com.company.gitaccessservice.dto.commit;

public class CommitDTO {
    private String title;
    private Integer commentCount;

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
