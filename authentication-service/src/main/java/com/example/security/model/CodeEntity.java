package com.example.security.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "code_entity")
public class CodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_entity_id_seq")
    @SequenceGenerator(name = "code_entity_id_seq", sequenceName = "code_entity_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "code")
    private String code;

    @Column(name = "time")
    private LocalDateTime expireTime;

    public CodeEntity() {
    }

    public CodeEntity(String username, String code, LocalDateTime expireTime) {
        this.username = username;
        this.code = code;
        this.expireTime = expireTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeEntity that = (CodeEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
