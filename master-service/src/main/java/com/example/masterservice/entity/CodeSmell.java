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
@Table(name = "code_smell")
public class CodeSmell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "analyze_report_id")
    private AnalyzeReport analyzeReport;

    public CodeSmell(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeSmell codeSmell = (CodeSmell) o;
        return Objects.equals(id, codeSmell.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
