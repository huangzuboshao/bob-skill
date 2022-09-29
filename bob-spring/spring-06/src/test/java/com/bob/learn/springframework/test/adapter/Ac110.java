package com.bob.learn.springframework.test.adapter;

/**
 * @author Bob
 * @date 2022/8/25 11:26
 */
public class Ac110 implements Ac{
    private static final int output = 110;

    @Override
    public int outputAc() {
        return output;
    }
}
