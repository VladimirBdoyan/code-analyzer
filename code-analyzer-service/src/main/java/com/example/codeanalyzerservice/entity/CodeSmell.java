package com.example.codeanalyzerservice.entity;

import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "code_smell", schema = "code_analyzer")
public class CodeSmell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "category")
    private CodeSmellCategory category;

    @Column(name = "message")
    private String message;

    @Column(name = "file")
    private String fileName;

    @ManyToOne
    @JoinColumn(name="analyze_result_id")
    private AnalyzeResult analyzeResult;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeSmell bugEntity = (CodeSmell) o;
        return Objects.equals(id, bugEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
