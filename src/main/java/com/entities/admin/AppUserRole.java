package com.entities.admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_USER_ROLE", uniqueConstraints = @UniqueConstraint(name = "APP_USER_ROLE_UK", columnNames = {"APP_USER_ID", "APP_ROLE_ID"}))
public class AppUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_USER_ROLE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long appUserRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_ROLE_ID", nullable = false)
    private AppRole appRole;

    public AppUserRole() {}

    public AppUserRole(AppUser appUser, AppRole appRole) {
        this.appUser = appUser;
        this.appRole = appRole;
    }

    public Long getAppUserRoleId() {
        return appUserRoleId;
    }

    public void setAppUserRoleId(Long appUserRoleId) {
        this.appUserRoleId = appUserRoleId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

}