package com.repositories.admin;

import com.entities.admin.AppAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppActionJpaRepository extends JpaRepository<AppAction, Long> {

    AppAction findAppActionByAppActionNameEquals(String appActionName);

}