package com.dahua.tech.easywork.gateway.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @auther jarod.jin 2018/11/30
 */
public class UsernameIsExitedException extends AuthenticationException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}
