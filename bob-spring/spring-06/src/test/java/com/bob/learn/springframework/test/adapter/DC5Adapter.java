package com.bob.learn.springframework.test.adapter;

/**
 * @author Bob
 * @date 2022/8/25 11:26
 */
public interface DC5Adapter {

    boolean support(Ac ac);

    int output5V(Ac ac);
}
