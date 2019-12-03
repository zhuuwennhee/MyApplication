package com.life.configurations;

import com.life.consts.AppRole;
import com.life.services.admin.impl.UserDetailsServiceImpl;
import com.life.supports.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        /**
         *  SET SERVICE FIND USER IN DATABASE
         *  SET PASSWORD ENCODE
         */
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                                    .passwordEncoder(this.passwordEncoder.getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        /** ALLOW RESOURCES **/
        http.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();

        /** ALLOW ALL ROLE **/
        http.authorizeRequests()
            .antMatchers("/",
                    "/index.html",
                    "/envelope.html",
                    "/sticky-note.html",
                    "/login.html",
                    "/register-account.html"
            )
            .permitAll();

        /** ALLOW IF SIGN-INNED **/
        http.authorizeRequests()
            .antMatchers("/profile.html"
            )
            .hasAnyRole(AppRole.GUEST, AppRole.MEMBER, AppRole.ADMINISTRATOR);

        /** ALLOW WITH MEMBER ROLE AND ADMIN ROLE **/
        http.authorizeRequests()
            .antMatchers("/user-manager.html"
            )
            .hasAnyRole(AppRole.MEMBER, AppRole.ADMINISTRATOR);

        /** ALLOW WITH ADMIN ROLE **/
        http.authorizeRequests()
            .antMatchers("/admin.html",
                    "/app-info.html"
            )
            .hasRole(AppRole.ADMINISTRATOR);

        /** LOGIN PAGE **/
        http.authorizeRequests()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .loginProcessingUrl("/j_spring_security_check")
            .failureHandler(this.authenticationFailureHandler)
            .defaultSuccessUrl("/index.html");

        /** REMEMBER ME **/
        http.authorizeRequests()
            .and()
            .rememberMe()
            .tokenRepository(this.persistentTokenRepository())
            .tokenValiditySeconds(1 * 24 * 60 * 60); /** 24 HOUR **/

        /** LOGOUT PAGE **/
        http.authorizeRequests()
            .and()
            .logout()
            .logoutSuccessUrl("/index.html");

        /** EXCEPTION HANDLE **/
            http.authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/403.html");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(this.dataSource);
        return jdbcTokenRepository;
    }

}