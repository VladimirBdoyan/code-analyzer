package com.example.findbugs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "analyze_report")
public class AnalyzeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    private Date date;

    @Column(name = "since")
    private Date since;

    @Column(name = "till")
    private Date till;

    @OneToOne(mappedBy = "analyzeReport")
    private AnalyzeResult analyzeResult;

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
