package com.bob.learn.springframework.test;

import com.bob.learn.springframework.test.adapter.Ac;
import com.bob.learn.springframework.test.adapter.DC5Adapter;

/**
 * @author Bob
 * @date 2022/8/25 11:37
 */
public class DefaultDc5Adapter implements DC5Adapter {
    @Override
    public boolean support(Ac ac) {
        return false;
    }

    @Override
    public int output5V(Ac ac) {
        return 0;
    }
}
