package com.example.security.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "authority", schema = "authentication")
public class Authority implements Serializable {

    @EmbeddedId
    private AuthorityID authorityID;

    public Authority(AuthorityID authorityID) {
        this.authorityID = authorityID;
    }

    public Authority() {
    }

    @Embeddable
    public static class AuthorityID implements Serializable {
        String username;
        String authority;

        public AuthorityID(String username, String authority) {
            this.username = username;
            this.authority = authority;
        }

        public AuthorityID() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }

    public AuthorityID getAuthorityID() {
        return authorityID;
    }

    public void setAuthorityID(AuthorityID authorityID) {
        this.authorityID = authorityID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(authorityID, authority.authorityID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityID);
    }
}
