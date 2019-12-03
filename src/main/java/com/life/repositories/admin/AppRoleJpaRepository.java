package com.life.repositories.admin;

import com.life.entities.admin.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleJpaRepository extends JpaRepository<AppRole, Long> {

    AppRole findAppRoleByAppRoleNameEquals(String appRoleName);

}