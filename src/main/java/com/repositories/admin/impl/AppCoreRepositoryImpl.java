package com.repositories.admin.impl;

import com.entities.admin.AppMenu;
import com.consts.AppRole;
import com.entities.admin.AppInfo;
import com.repositories.admin.AppCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AppCoreRepositoryImpl implements AppCoreRepository {

    @Autowired
    private EntityManager entityManager;

    /**
     *  LOAD APPLICATION INFO CONFIG
     *  BRAND NAME
     *  LOCATION
     *  DEVELOPER
     *  COPYRIGHT ...
     */
    @Transactional
    @Override
    public AppInfo loadAppInfo() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AppInfo> criteriaQuery = criteriaBuilder.createQuery(AppInfo.class);
        Root<AppInfo> appInfoRoot = criteriaQuery.from(AppInfo.class);
        criteriaQuery.select(appInfoRoot);
        return this.entityManager.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(new AppInfo());
    }

    /**
     *  LOAD NAV MENU BY PERMISSION VISIT
     *  IF PERMISSION = GUEST -> HOME, ENVELOPE, STICK NOTE
     *  IF PERMISSION = MEMBER -> GUEST + CLOUD UPLOAD
     *  IF PERMISSION = ADMIN -> ALL MENU
     */
    @Transactional
    @Override
    public List<AppMenu> loadAppMenuByAppRoleName(String appRoleName) {
        List<String> appMenuNameList = new ArrayList<>();
        switch (appRoleName) {
            case AppRole.ROLE_ADMINISTRATOR: {
                appMenuNameList.addAll(
                        Arrays.asList(
                                com.consts.AppMenu.HOME,
                                com.consts.AppMenu.ENVELOPE,
                                com.consts.AppMenu.STICK_NOTE,
                                com.consts.AppMenu.CLOUD_UPLOAD,
                                com.consts.AppMenu.VIDEO_CAMERA));
                break;
            }
            case AppRole.ROLE_MEMBER: {
                appMenuNameList.addAll(
                        Arrays.asList(
                                com.consts.AppMenu.HOME,
                                com.consts.AppMenu.ENVELOPE,
                                com.consts.AppMenu.STICK_NOTE,
                                com.consts.AppMenu.CLOUD_UPLOAD));
                break;
            }
            case AppRole.ROLE_GUEST:
            default: {
                appMenuNameList.addAll(
                        Arrays.asList(
                                com.consts.AppMenu.HOME,
                                com.consts.AppMenu.ENVELOPE,
                                com.consts.AppMenu.STICK_NOTE));
                break;
            }
        }
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AppMenu> criteriaQuery = criteriaBuilder.createQuery(AppMenu.class);
        Root<AppMenu> appMenuRoot = criteriaQuery.from(AppMenu.class);
        criteriaQuery.select(appMenuRoot)
                     .where(criteriaBuilder.in(appMenuRoot.get("appMenuName")).value(appMenuNameList))
                     .orderBy(criteriaBuilder.asc(appMenuRoot.get("sequence")));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

}