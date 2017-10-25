package com.zhanglifan.exception;

/**
 * Information
 * Author: ZhangLifan
 * Time: 2017/10/25 9:56
 * Description:
 */
public class CustomException extends Exception {
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
