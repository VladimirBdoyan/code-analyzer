package com.example.masterservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "master_service_report", schema = "master")
@Setter
@Getter
@NoArgsConstructor
public class AnalyzeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @Column(name = "developerCommitDensity")
    private double developerCommitDensity;

    @Column(name = "pullRequestDensity")
    private double pullRequestDensity;

    @Column(name = "pullRequestCommitDensity")
    private double pullRequestCommitDensity;

    @Column(name = "conversationCount")
    private int conversationCount;

    @Column(name = "pullRequestCommitCommentCount")
    private int pullRequestCommitCommentCount;

    @Column(name = "coding_rate")
    private int codingRate;

    @OneToMany(mappedBy = "analyzeReport")
    private List<BugEntity> bugs;

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
