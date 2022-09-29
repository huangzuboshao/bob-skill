package com.bob.learn.springframework.beans.factory;

import com.bob.learn.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.bob.learn.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author Bob
 * @date 2022/8/18 17:48
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
}
