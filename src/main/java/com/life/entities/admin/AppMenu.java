package com.life.entities.admin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "APP_MENU")
public class AppMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_MENU_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appMenuId;

    @Column(name = "APP_MENU_NAME", nullable = false, length = 32)
    private String appMenuName;

    @Column(name = "PAGE_TITLE", length = 64)
    private String pageTitle;

    @Column(name = "PAGE_URL", length = 64)
    private String pageURL;

    @Column(name = "FONT_AWESOME_ICON", length = 32)
    private String fontAwesomeIcon;

    @Column(name = "SEQUENCE", nullable = false)
    private Integer sequence;

    @OneToMany(mappedBy = "appMenu", fetch = FetchType.EAGER)
    @OrderBy(value = "SEQUENCE ASC")
    private Set<AppSubMenu> appSubMenuSet;

    public AppMenu() {}

    public AppMenu(String appMenuName, String pageTitle, String pageURL, String fontAwesomeIcon, Integer sequence, Set<AppSubMenu> appSubMenuSet) {
        this.appMenuName = appMenuName;
        this.pageTitle = pageTitle;
        this.pageURL = pageURL;
        this.fontAwesomeIcon = fontAwesomeIcon;
        this.sequence = sequence;
        this.appSubMenuSet = appSubMenuSet;
    }

    public Long getAppMenuId() {
        return appMenuId;
    }

    public void setAppMenuId(Long appMenuId) {
        this.appMenuId = appMenuId;
    }

    public String getAppMenuName() {
        return appMenuName;
    }

    public void setAppMenuName(String appMenuName) {
        this.appMenuName = appMenuName;
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

    public Set<AppSubMenu> getAppSubMenuSet() {
        return appSubMenuSet;
    }

    public void setAppSubMenuSet(Set<AppSubMenu> appSubMenuSet) {
        this.appSubMenuSet = appSubMenuSet;
    }

}