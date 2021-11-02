package com.bezkoder.springjwt.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationManager implements AuthenticationManager{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pw       = authentication.getCredentials().toString();

        System.out.println(username);
        System.out.println(pw);
        
        return null;
    }

}
