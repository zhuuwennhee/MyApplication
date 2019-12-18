package com.entities.admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_ROLE")
public class AppRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_ROLE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appRoleId;

    @Column(name = "APP_ROLE_NAME", unique = true, nullable = false, length = 32)
    private String appRoleName;

    public AppRole() {}

    public AppRole(String appRoleName) {
        this.appRoleName = appRoleName;
    }

    public Long getAppRoleId() {
        return appRoleId;
    }

    public void setAppRoleId(Long appRoleId) {
        this.appRoleId = appRoleId;
    }

    public String getAppRoleName() {
        return appRoleName;
    }

    public void setAppRoleName(String appRoleName) {
        this.appRoleName = appRoleName;
    }

}