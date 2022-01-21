package com.zzh.estore.service.ex;

/**
 * @author ：zzh
 * @description ：更新异常
 * @date ：Created in 2022/1/9 13:53
 */
// 用户在更新数据时产生的未知异常
public class UpdateException extends ServiceException{
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
