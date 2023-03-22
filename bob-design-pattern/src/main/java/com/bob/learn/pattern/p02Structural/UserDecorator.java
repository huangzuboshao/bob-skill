package com.bob.learn.pattern.p02Structural;

/**
 * 装饰器模式【结构型】
 *
 * @author huangzuboshao
 * @date 2022/12/14 19:33
 */
public class UserDecorator extends User {

    private final User user;

    public UserDecorator(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        User user = new User();
        user.m1();
        System.out.println("---- 装饰增强 ----");
        User userProxy = new UserDecorator(user);
        userProxy.m1();
    }

    @Override
    public void m1() {
        System.out.println("装饰类增强m1()");
        user.m1();
    }
}
