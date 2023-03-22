package com.bob.learn.pattern.p02Structural;

/**
 * 代理模式
 *
 * @author huangzuboshao
 * @date 2022/12/14 19:33
 */
public class UserProxy extends User {
    public static void main(String[] args) {
        User user = new User();
        user.m1();
        System.out.println("---- 代理增强 ----");
        User userProxy = new UserProxy();
        userProxy.m1();
    }

    @Override
    public void m1() {
        System.out.println("代理类增强m1()");
        super.m1();
    }
}
