package com.bob.learn.springframework.test.bean;

/**
 * 用户服务
 *
 * @author Bob
 * @date 2022/7/13 15:37
 */
public class UserService {
    private String username;

    public UserService() {
    }

    public UserService(String username) {
        this.username = username;
    }

    public void queryUserInfo() {
        System.out.println(this.toString() + " 查询用户信息[" + username + "]");
    }
}
