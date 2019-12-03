package com.life.entities.admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_INFO")
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_INFO_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appInfoId;

    @Column(name = "APP_NAV_BRAND", nullable = false, length = 32)
    private String appNavBrand;

    @Column(name = "COPYRIGHT", nullable = false, length = 128)
    private String copyright;

    @Column(name = "DEVELOPER", nullable = false, length = 128)
    private String developer;

    @Column(name = "LOCATION", nullable = false, length = 256)
    private String location;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 32)
    private String phoneNumber;

    @Column(name = "EMAIL_ADDRESS", nullable = false, length = 64)
    private String emailAddress;

    @Column(name = "FACEBOOK", length = 128)
    private String facebook;

    @Column(name = "SKYPE", length = 128)
    private String skype;

    public AppInfo() {}

    public AppInfo(String appNavBrand, String copyright, String developer, String location, String phoneNumber, String emailAddress, String facebook, String skype) {
        this.appNavBrand = appNavBrand;
        this.copyright = copyright;
        this.developer = developer;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.facebook = facebook;
        this.skype = skype;
    }

    public Long getAppInfoId() {
        return appInfoId;
    }

    public void setAppInfoId(Long appInfoId) {
        this.appInfoId = appInfoId;
    }

    public String getAppNavBrand() {
        return appNavBrand;
    }

    public void setAppNavBrand(String appNavBrand) {
        this.appNavBrand = appNavBrand;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

}