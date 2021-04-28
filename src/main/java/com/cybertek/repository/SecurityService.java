package com.cybertek.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

}
/*
here we have our business logic we retrieve a user from DB and provide that user to Spring framework ,
and Spring will pass to USerDetails(convert)  to handle authority
 */