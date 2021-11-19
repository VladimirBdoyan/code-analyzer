package com.example.analyzedeveloper.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bug_entity")
public class BugEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "file")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "analyze_report_id")
    private AnalyzeReport analyzeReport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugEntity bugEntity = (BugEntity) o;
        return Objects.equals(id, bugEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
