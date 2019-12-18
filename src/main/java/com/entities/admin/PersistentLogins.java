package com.entities.admin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogins implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SERIES", nullable = false, length = 64)
    private String series;

    @Column(name = "USERNAME", nullable = false, length = 64)
    private String username;

    @Column(name = "TOKEN", nullable = false, length = 64)
    private String token;

    @Column(name = "LAST_USED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public PersistentLogins() {}

    public PersistentLogins(String series, String username, String token, Date lastUsed) {
        this.series = series;
        this.username = username;
        this.token = token;
        this.lastUsed = lastUsed;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

}