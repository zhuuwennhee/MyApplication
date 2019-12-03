package com.life.entities.admin;

import com.life.entities.user.AppUserProfile;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "APP_USER")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appUserId;

    @Column(name = "APP_USER_NAME", unique = true, nullable = false, length = 64)
    private String appUsername;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false, length = 128)
    private String encryptedPassword;

    @Column(name = "CREATED_DATE", nullable = false)
    @ColumnDefault("GETDATE()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "ENABLED", length = 1, nullable = false)
    @ColumnDefault("'FALSE'")
    private boolean enabled;

    @Column(name = "ENABLED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enabledDate;

    @Column(name = "LOCKED", length = 1, nullable = false)
    @ColumnDefault("'FALSE'")
    private boolean locked;

    @Column(name = "LOCKED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockedDate;

    @Column(name = "UNLOCKED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unlockedDate;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "appUser")
    private AppUserProfile appUserProfile;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appUser")
    private Set<AppUserRole> appUserRoleSet;

    public AppUser() {}

    public AppUser(String appUsername, String encryptedPassword, Date createdDate, boolean enabled, Date enabledDate, boolean locked, Date lockedDate, Date unlockedDate, AppUserProfile appUserProfile, Set<AppUserRole> appUserRoleSet) {
        this.appUsername = appUsername;
        this.encryptedPassword = encryptedPassword;
        this.createdDate = createdDate;
        this.enabled = enabled;
        this.enabledDate = enabledDate;
        this.locked = locked;
        this.lockedDate = lockedDate;
        this.unlockedDate = unlockedDate;
        this.appUserProfile = appUserProfile;
        this.appUserRoleSet = appUserRoleSet;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getEnabledDate() {
        return enabledDate;
    }

    public void setEnabledDate(Date enabledDate) {
        this.enabledDate = enabledDate;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Date getUnlockedDate() {
        return unlockedDate;
    }

    public void setUnlockedDate(Date unlockedDate) {
        this.unlockedDate = unlockedDate;
    }

    public AppUserProfile getAppUserProfile() {
        return appUserProfile;
    }

    public void setAppUserProfile(AppUserProfile appUserProfile) {
        this.appUserProfile = appUserProfile;
    }

    public Set<AppUserRole> getAppUserRoleSet() {
        return appUserRoleSet;
    }

    public void setAppUserRoleSet(Set<AppUserRole> appUserRoleSet) {
        this.appUserRoleSet = appUserRoleSet;
    }

}