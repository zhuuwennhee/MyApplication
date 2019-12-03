package com.life.supports;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    /**
     *  GET BCRYPT PASSWORD ENCODER
     */
    public org.springframework.security.crypto.password.PasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *  HASH PASSWORD BY BCrypt
     */
    public String encryptPassword(String password) {
        return getBCryptPasswordEncoder().encode(password);
    }

    /**
     *  MATCHES PASSWORD
     */
    public boolean matches(String password, String encryptedPassword) {
        return this.getBCryptPasswordEncoder().matches(password, encryptedPassword);
    }

}