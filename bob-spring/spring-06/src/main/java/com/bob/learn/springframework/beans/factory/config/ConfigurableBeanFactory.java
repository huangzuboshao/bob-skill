package com.bob.learn.springframework.beans.factory.config;

import com.bob.learn.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 允许Bean工厂自定义配置，如是单例模式还是原型模式
 * @author Bob
 * @date 2022/8/18 17:09
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
