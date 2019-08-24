package com.hao.exception;

/**
 * @Describe com.hao.exception
 * @Auther wenhao chen
 * @CreateDate 2019/8/22
 * @Version 1.0
 * 自定义异常类
 */
public class MyException extends Exception{
    //异常信息
    private String message;

    public MyException(String message) {
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
