package com.example.masterservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "analyze_report")
public class AnalyzeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @Column(name = "developer_commit_density")
    private double developerCommitDensity;

    @Column(name = "pull_request_density")
    private double pullRequestDensity;

    @Column(name = "pull_request_commit_density")
    private double pullRequestCommitDensity;

    @Column(name = "conversation_count")
    private int conversationCount;

    @Column(name = "pull_request_commit_comment_count")
    private int pullRequestCommitCommentCount;

    @Column(name = "coding_rate")
    private int codingRate;

    @OneToMany
    @JoinColumn(name = "analyze_report_id")
    private List<CodeSmell> codeSmells;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzeReport that = (AnalyzeReport) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
