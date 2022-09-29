package com.bob.learn.springframework.test.adapter;

/**
 * @author Bob
 * @date 2022/8/25 11:18
 */
public class Ac220 implements Ac {

    private static final int output = 220;

    @Override
    public int outputAc() {
        return output;
    }
}
