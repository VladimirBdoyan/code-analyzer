package com.example.analyzedeveloper.entity;

import com.example.analyzedeveloper.entity.enums.ReportStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "analyze_report_status")
@Setter
@Getter
@NoArgsConstructor
public class AnalyzeState {

    @Id
    private UUID jobId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "finishDate")
    private Date finishDate;

    @Column(name = "fail_reason")
    private String failReason;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "analyze_report_id")
    private AnalyzeReport analyzeReport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzeState that = (AnalyzeState) o;
        return Objects.equals(jobId, that.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}
