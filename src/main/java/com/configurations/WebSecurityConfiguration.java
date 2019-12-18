package com.configurations;

import com.consts.AppRole;
import com.consts.AppURI;
import com.services.admin.impl.UserDetailsServiceImpl;
import com.supports.PasswordEncoder;
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
            .antMatchers(AppURI.ROOT, AppURI.INDEX, AppURI.LOGIN, AppURI.CONFIRM_REGISTRATION)
            .permitAll();

        /** ALLOW IF SIGN-INNED **/
        http.authorizeRequests()
            .antMatchers(AppURI.PROFILE)
            .hasAnyRole(AppRole.GUEST, AppRole.MEMBER, AppRole.ADMINISTRATOR);

        /** ALLOW WITH MEMBER ROLE AND ADMIN ROLE **/
        http.authorizeRequests()
            .antMatchers(AppURI.USER_MANAGER)
            .hasAnyRole(AppRole.MEMBER, AppRole.ADMINISTRATOR);

        /** ALLOW WITH ADMIN ROLE **/
        http.authorizeRequests()
            .antMatchers(AppURI.ADMINISTRATOR, AppURI.APP_INFO)
            .hasRole(AppRole.ADMINISTRATOR);

        /** LOGIN PAGE **/
        http.authorizeRequests()
            .and()
            .formLogin()
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .loginProcessingUrl("/j_spring_security_check")
            .failureHandler(this.authenticationFailureHandler)
            .defaultSuccessUrl(AppURI.INDEX);

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
            .logoutSuccessUrl(AppURI.INDEX);

        /** EXCEPTION HANDLE **/
            http.authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage(AppURI.ERROR_403);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(this.dataSource);
        return jdbcTokenRepository;
    }

}