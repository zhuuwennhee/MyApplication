package com.life.entities.user;

import com.life.entities.admin.AppUser;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "APP_USER_PROFILE")
public class AppUserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_USER_PROFILE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appUserProfileId;

    @Column(name = "EMAIL_ADDRESS", nullable = false, length = 64)
    @Email
    private String emailAddress;

    @Column(name = "FIRST_NAME", length = 32)
    private String firstName;

    @Column(name = "LAST_NAME", length = 32)
    private String lastName;

    @Column(name = "GENDER", nullable = false)
    @ColumnDefault("'FALSE'")
    private boolean gender;

    @Column(name = "AVATAR", length = 252)
    private String avatar;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "ADDRESS", length = 512)
    private String address;

    @Column(name = "PHONE_NUMBER", length = 16)
    private String phoneNumber;

    @Column(name = "LAST_UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_USER_ID")
    private AppUser appUser;

    public AppUserProfile() {}

    public AppUserProfile(@Email String emailAddress, String firstName, String lastName, boolean gender, String avatar, Date dateOfBirth, String address, String phoneNumber, Date lastUpdatedDate, AppUser appUser) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.avatar = avatar;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.lastUpdatedDate = lastUpdatedDate;
        this.appUser = appUser;
    }

    public Long getAppUserProfileId() {
        return appUserProfileId;
    }

    public void setAppUserProfileId(Long appUserProfileId) {
        this.appUserProfileId = appUserProfileId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}