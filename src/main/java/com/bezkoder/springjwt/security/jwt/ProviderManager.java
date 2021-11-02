package com.bezkoder.springjwt.security.jwt;

import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import java.util.logging.*;
import java.util.logging.Logger;


public class ProviderManager implements AuthenticationManager, MessageSourceAware, InitializingBean{

   private List<AuthenticationProvider> providers = Collections.emptyList();

   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProviderManager.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Class<? extends Authentication> toTest = authentication.getClass();
        AuthenticationException lastException = null;
        AuthenticationException parentException = null;
        Authentication result = null;
        Authentication parentResult = null;
        boolean debug = logger.isDebugEnabled();
        
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        System.out.println(username);
        System.out.println(password);
        
        return null;
//        for (AuthenticationProvider provider : get()) {
//            if (!provider.supports(toTest)) {
//                continue;
//            }
//            if (debug) {
//                logger.debug("Authentication attempt using "
//                        + provider.getClass().getName());
//            }
//            try {
//                result = provider.authenticate(authentication);
//                if (result != null) {
//                    copyDetails(authentication, result);
//                    break;
//                }
//            }
//            catch (AccountStatusException | InternalAuthenticationServiceException e) {
//                prepareException(e, authentication);
//                // SEC-546: Avoid polling additional providers if auth failure is due to
//                throw e;
//            } catch (AuthenticationException e) {
//                lastException = e;
//            }
//    }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        // TODO Auto-generated method stub
        
    }





}
