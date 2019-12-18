package com.entities.admin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APP_SUB_MENU")
public class AppSubMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_SUB_MENU_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appSubMenuId;

    @Column(name = "APP_SUB_MENU_NAME", nullable = false, length = 64)
    private String appSubMenuName;

    @Column(name = "PAGE_TITLE", length = 64)
    private String pageTitle;

    @Column(name = "PAGE_URL", length = 64)
    private String pageURL;

    @Column(name = "FONT_AWESOME_ICON", length = 32)
    private String fontAwesomeIcon;

    @Column(name = "SEQUENCE", nullable = false)
    private Integer sequence;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APP_MENU_ID_PARENT", nullable = false)
    private AppMenu appMenu;

    public AppSubMenu() {}

    public AppSubMenu(String appSubMenuName, String pageTitle, String pageURL, String fontAwesomeIcon, Integer sequence, AppMenu appMenu) {
        this.appSubMenuName = appSubMenuName;
        this.pageTitle = pageTitle;
        this.pageURL = pageURL;
        this.fontAwesomeIcon = fontAwesomeIcon;
        this.sequence = sequence;
        this.appMenu = appMenu;
    }

    public Long getAppSubMenuId() {
        return appSubMenuId;
    }

    public void setAppSubMenuId(Long appSubMenuId) {
        this.appSubMenuId = appSubMenuId;
    }

    public String getAppSubMenuName() {
        return appSubMenuName;
    }

    public void setAppSubMenuName(String appSubMenuName) {
        this.appSubMenuName = appSubMenuName;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getFontAwesomeIcon() {
        return fontAwesomeIcon;
    }

    public void setFontAwesomeIcon(String fontAwesomeIcon) {
        this.fontAwesomeIcon = fontAwesomeIcon;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public AppMenu getAppMenu() {
        return appMenu;
    }

    public void setAppMenu(AppMenu appMenu) {
        this.appMenu = appMenu;
    }

}