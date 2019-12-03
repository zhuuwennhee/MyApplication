package com.life.repositories.admin;

import com.life.entities.admin.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRoleJpaRepository extends JpaRepository<AppUserRole, Long> {}