package com.repositories.admin.impl;

import com.entities.admin.AppUser;
import com.entities.admin.AppUserRole;
import com.repositories.admin.AppRoleJpaRepository;
import com.repositories.admin.AppUserRoleJpaRepository;
import com.repositories.admin.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AppUserRoleRepositoryImpl implements AppUserRoleRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AppUserRoleJpaRepository appUserRoleJpaRepository;

    @Autowired
    private AppRoleJpaRepository appRoleJpaRepository;

    /**
     *  LOAD ROLE NAME LIST FOR USER BY USER_ID
     */
    @Transactional
    @Override
    public List<String> getAppRoleNameListByAppUserId(Long appUserId) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<AppUserRole> appUserRoleRoot = criteriaQuery.from(AppUserRole.class);
        criteriaQuery.select(appUserRoleRoot.get("appRole").get("appRoleName"))
                     .where(criteriaBuilder.equal(appUserRoleRoot.get("appUser").get("appUserId"), appUserId))
                     .orderBy(criteriaBuilder.asc(appUserRoleRoot.get("appRole").get("appRoleName")));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     *  LOAD ROLE NAME LIST FOR USER BY USER_NAME
     */
    @Transactional
    @Override
    public List<String> getAppRoleNameListByAppUsername(String appUsername) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<AppUserRole> appUserRoleRoot = criteriaQuery.from(AppUserRole.class);
        criteriaQuery.select(appUserRoleRoot.get("appRole").get("appRoleName"))
                     .where(criteriaBuilder.equal(appUserRoleRoot.get("appUser").get("appUsername"), appUsername))
                     .orderBy(criteriaBuilder.asc(appUserRoleRoot.get("appRole").get("appRoleName")));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     *  INSERT LIST APP ROLE OF APP USER
     */
    @Override
    public void createAppUserRoleList(Set<AppUserRole> appUserRoleSet, AppUser appUser) {
        appUserRoleSet = appUserRoleSet.stream()
                                       .map(appUserRole -> {
                                            appUserRole.setAppUser(appUser);
                                            appUserRole.setAppRole(this.appRoleJpaRepository.findAppRoleByAppRoleNameEquals(appUserRole.getAppRole().getAppRoleName()));
                                            return appUserRole;
                                       })
                                       .collect(Collectors.toSet());
        this.appUserRoleJpaRepository.saveAll(appUserRoleSet);
        this.appUserRoleJpaRepository.flush();
    }

}