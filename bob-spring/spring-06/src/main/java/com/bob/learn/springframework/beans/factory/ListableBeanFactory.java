package com.bob.learn.springframework.beans.factory;

import com.bob.learn.springframework.beans.BeansException;

import java.util.Map;

/**
 * 可列表的BeanFactory
 * @author Bob
 * @date 2022/8/18 17:18
 */
public interface ListableBeanFactory extends BeanFactory {


    /**
     *
     * @param type 类型
     * @return
     */
    String[] getBeanNamesForType(Class<?> type);

    /**
     *
      * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}
