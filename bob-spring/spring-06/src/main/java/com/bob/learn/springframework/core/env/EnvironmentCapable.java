package com.bob.learn.springframework.core.env;

/**
 * 环境能力
 *
 * @author Bob
 * @date 2022/8/25 15:05
 */
public interface EnvironmentCapable {

    /**
     * 获取组件关联的Environment
     * @return
     */
    Environment getEnvironment();
}
