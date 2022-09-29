package com.bob.learn.springframework.test.bean;

/**
 * @author Bob
 * @date 2022/7/14 17:26
 */
public interface MemberService {

    default void getMember() {
        System.out.println(this.toString() + " 查询会员信息");
    }
}
