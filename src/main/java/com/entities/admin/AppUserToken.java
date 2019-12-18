package com.entities.admin;

import com.utils.DateTimeUtils;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "APP_USER_TOKEN")
public class AppUserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APP_USER_TOKEN_ID")
    private Long appUserTokenId;

    @Column(name = "APP_USER_TOKEN_VALUE")
    private String appUserTokenValue;

    @Column(name = "GENERATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("GETDATE()")
    private Date generatedDate;

    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_ACTION_ID", nullable = false)
    private AppAction appAction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    public AppUserToken() {}

    public AppUserToken(AppUser appUser, AppAction appAction) {
        this.appUser = appUser;
        this.appUserTokenValue = UUID.randomUUID().toString();
        this.generatedDate = appUser.getCreatedDate();
        this.expirationDate = DateTimeUtils.plusDays(this.generatedDate, 7);
        this.appAction = appAction;
    }

    public AppUserToken(String appUserTokenValue, Date generatedDate, Date expirationDate, AppAction appAction, AppUser appUser) {
        this.appUserTokenValue = appUserTokenValue;
        this.generatedDate = generatedDate;
        this.expirationDate = expirationDate;
        this.appAction = appAction;
        this.appUser = appUser;
    }

    public Long getAppUserTokenId() {
        return appUserTokenId;
    }

    public void setAppUserTokenId(Long appUserTokenId) {
        this.appUserTokenId = appUserTokenId;
    }

    public String getAppUserTokenValue() {
        return appUserTokenValue;
    }

    public void setAppUserTokenValue(String appUserTokenValue) {
        this.appUserTokenValue = appUserTokenValue;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
        if(generatedDate != null) {
            this.expirationDate = DateTimeUtils.plusDays(generatedDate, 7);
        }
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AppAction getAppAction() {
        return appAction;
    }

    public void setAppAction(AppAction appAction) {
        this.appAction = appAction;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}