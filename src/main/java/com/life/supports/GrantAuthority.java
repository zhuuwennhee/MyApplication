package com.life.supports;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GrantAuthority {

    public String toString(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            stringBuilder.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    stringBuilder.append(a.getAuthority());
                    first = false;
                }
                else {
                    stringBuilder.append(", ").append(a.getAuthority());
                }
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

}