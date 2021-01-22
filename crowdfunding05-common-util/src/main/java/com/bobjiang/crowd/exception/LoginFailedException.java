package com.bobjiang.crowd.exception;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-23 2:07
 * 登录失败后抛出的异常
 */
public class LoginFailedException extends RuntimeException{

        public LoginFailedException() {
            super();
        }

        public LoginFailedException(String message) {
            super(message);
        }

        public LoginFailedException(String message, Throwable cause) {
            super(message, cause);
        }

        public LoginFailedException(Throwable cause) {
            super(cause);
        }

        protected LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
