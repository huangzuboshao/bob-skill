package com.bob.learn.springframework.context;

import com.bob.learn.springframework.beans.factory.HierarchicalBeanFactory;
import com.bob.learn.springframework.beans.factory.ListableBeanFactory;
import com.bob.learn.springframework.core.env.EnvironmentCapable;

/**
 * 上下文
 * @author Bob
 * @date 2022/8/25 15:02
 */
public interface ApplicationContext extends EnvironmentCapable,ListableBeanFactory, HierarchicalBeanFactory {
}
