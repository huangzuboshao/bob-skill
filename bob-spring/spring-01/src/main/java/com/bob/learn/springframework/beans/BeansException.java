package com.bob.learn.springframework.beans;

/**
 * Bean异常
 *
 * @author Bob
 * @date 2022/7/13 15:33
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
