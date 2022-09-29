package com.bob.learn.springframework.core.io;

/**
 * 包装资源加载器
 * @author Bob
 * @date 2022/8/17 17:57
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 获取资源
     * @param location
     * @return
     */
    Resource getResource(String location);
}
