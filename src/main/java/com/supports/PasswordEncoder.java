package com.supports;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

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
     *  GENERATED RANDOM PASSWORD
     */
    public String generatedPassword() {
        String allow = "qwertyuioplkjhgfdsazxcvbnm0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
        String password = "";
        Random random = new SecureRandom();
        for(int i = 0; i < 16; ++i) {
            password += allow.charAt(random.nextInt(allow.length()));
        }
        return password;
    }

    /**
     *  MATCHES PASSWORD
     */
    public boolean matches(String password, String encryptedPassword) {
        return this.getBCryptPasswordEncoder().matches(password, encryptedPassword);
    }

}