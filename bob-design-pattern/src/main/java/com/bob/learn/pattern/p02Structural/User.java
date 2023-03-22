package com.bob.learn.pattern.p02Structural;

import java.util.ArrayList;
import java.util.LinkedList;

public class User {
    private String id;
    private transient String password;
    private String name;

    public void m1() {
        System.out.println("invoke User.m1()");
    }

    public void m2() {
    }

}