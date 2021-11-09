package com.company.statisticsservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "pullrequest")
public class GitPullRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pullrequest_id_seq")
    @SequenceGenerator(name = "pullrequest_id_seq", sequenceName = "pullrequest_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private GitUser gitUser;

}
