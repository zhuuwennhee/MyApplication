package com.entities.admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_ACTION")
public class AppAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_ACTION_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appActionId;

    @Column(name = "APP_ACTION_NAME", nullable = false, unique = true, length = 32)
    private String appActionName;

    public AppAction() {}

    public AppAction(String appActionName) {
        this.appActionName = appActionName;
    }

    public AppAction(Long appActionId, String appActionName) {
        this.appActionId = appActionId;
        this.appActionName = appActionName;
    }

    public Long getAppActionId() {
        return appActionId;
    }

    public void setAppActionId(Long appActionId) {
        this.appActionId = appActionId;
    }

    public String getAppActionName() {
        return appActionName;
    }

    public void setAppActionName(String appActionName) {
        this.appActionName = appActionName;
    }

}