package com.cybertek.config;

import com.cybertek.implemetation.SecurityServiceImpl;
import com.cybertek.repository.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
1. create websecurity class add @configuration, Enable Wevsecutity extend Configure adapter
2. override 1 method bc we do not need inmemory , we will use only db    protected void configure(HttpSecurity http) throws Exception {
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private SecurityService securityService;
    private AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http /// here we specify specific role has specific access to different pages
                .authorizeRequests()
                .antMatchers("/user/**").hasAuthority("Admin")
                .antMatchers("/project/**").hasAuthority("Manager")
                .antMatchers("/task/employee/**").hasAuthority("Employee")
                .antMatchers("/task/**").hasAuthority("Manager")
                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll()    // all public access for everyone
                 .and()
                 .formLogin()
                 .loginPage("/login")
              //   .defaultSuccessUrl("/welcome")
                  .successHandler(authSuccessHandler)
                 .failureUrl("/login?error=true")
                 .permitAll()
                 .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .tokenValiditySeconds(120)
                .key("cybertekSecret")    // connect cookies and that key
                .userDetailsService(securityService); // which user to remember
    }


}
