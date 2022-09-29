package com.bob.learn.springframework.context;

import com.bob.learn.springframework.beans.BeansException;

/**
 * 可配置的ApplicationContext
 *
 * @author Bob
 * @date 2022/8/25 15:09
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
