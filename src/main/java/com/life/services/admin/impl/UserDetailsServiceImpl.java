package com.life.services.admin.impl;

import com.life.consts.ExceptionMessage;
import com.life.entities.admin.AppUser;
import com.life.repositories.admin.AppUserRepository;
import com.life.repositories.admin.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service(value = "AppUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger("USER_DETAILS_SERVICE_IMPL");

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserRoleRepository appUserRoleRepository;

    /**
     *  LOAD USER BY USER_NAME
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException, LockedException {
        AppUser appUser = this.appUserRepository.loadAppUserByAppUsername(username);
        /** USER NOT FOUND **/
        if(appUser == null) {
            this.LOGGER.log(Level.WARNING, "USER: " + username + " NOT FOUND!");
            throw new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND);
        }
        /** USER FOUND BUT DISABLED **/
        if(!appUser.isEnabled()) {
            this.LOGGER.log(Level.WARNING, "USER: " + username + " DISABLED!");
            throw new DisabledException(ExceptionMessage.USER_DISABLED);
        }
        /** USER FOUND BUT LOCKED **/
        if(appUser.isLocked()) {
            this.LOGGER.log(Level.WARNING, "USER: " + username + " LOCKED!");
            throw new LockedException(ExceptionMessage.USER_LOCKED);
        }
        /**
         *  FOUND USER
         *  LOAD ALL USER ROLE NAME
         */
        List<String> roleNameList = this.appUserRoleRepository.getAppRoleNameListByAppUserId(appUser.getAppUserId());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if(roleNameList != null) {
            for (String roleName : roleNameList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                grantedAuthorityList.add(authority);
            }
        }
        return new User(appUser.getAppUsername(), appUser.getEncryptedPassword(), grantedAuthorityList);
    }

}