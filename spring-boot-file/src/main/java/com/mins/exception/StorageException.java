package com.mins.exception;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/29
 * No struggle, talent how to match the willfulness.
 * Description:
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
