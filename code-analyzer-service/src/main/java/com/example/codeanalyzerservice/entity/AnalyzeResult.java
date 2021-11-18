package com.example.findbugs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "analyze_result",schema = "check_code")
public class AnalyzeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_rate")
    private int currentRate;

    @Column(name = "max_rate")
    private int maxRate;

    @OneToMany(mappedBy = "analyzeResult")
    List<CodeSmell> codeSmells=new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "analyze_report_id")
    private AnalyzeReport analyzeReport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzeResult that = (AnalyzeResult) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
