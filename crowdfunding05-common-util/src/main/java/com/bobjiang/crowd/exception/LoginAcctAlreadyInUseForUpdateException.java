package com.bobjiang.crowd.exception;

/** 检测到登录账号重复的异常
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-23 19:23
 */
public class LoginAcctAlreadyInUseForUpdateException extends RuntimeException  {
    public LoginAcctAlreadyInUseForUpdateException() {
        super();
    }

    public LoginAcctAlreadyInUseForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
