package com.cybertek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

// we need that class to specify (customize) a page after user did a login what a user shou;d see ( which page )
@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // authentication.getAuthorities()); is comming from our user principles authorities which we created
         Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if(roles.contains("Admin")){
            httpServletResponse.sendRedirect("/user/create");
        }
        if(roles.contains("Manager")){
            httpServletResponse.sendRedirect("/task/create");
        }
        if(roles.contains("Employee")){
            httpServletResponse.sendRedirect("/task/employee");
        }

    }
}
