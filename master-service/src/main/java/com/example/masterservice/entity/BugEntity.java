package com.example.masterservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bug_entity", schema = "master")
public class BugEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "file")
    private String fileName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "analyze_report_id" )
    private AnalyzeReport analyzeReport;

    public BugEntity(String message) {
        this.message = message;
    }

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
