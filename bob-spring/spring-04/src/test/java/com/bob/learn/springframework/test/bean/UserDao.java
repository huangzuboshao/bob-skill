package com.bob.learn.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bob
 * @date 2022/7/15 11:32
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "bob");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
