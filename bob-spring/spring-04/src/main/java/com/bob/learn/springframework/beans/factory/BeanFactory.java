package com.bob.learn.springframework.beans.factory;

import com.bob.learn.springframework.beans.BeansException;
import com.bob.learn.springframework.beans.factory.config.BeanDefinition;
import com.sun.istack.internal.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂
 *
 * @author Bob
 * @date 2022/7/13 15:26
 */
public interface BeanFactory {

    /**
     * 返回指定 bean 的一个实例
     *
     * @param name bean 的名称
     * @return bean 的一个实例
     * @throws BeansException 如果没有指定名称的 bean 定义
     *                        BeansException – 如果无法获得 bean
     */
    Object getBean(String name) throws BeansException;

    /**
     * 返回指定 bean 的一个实例
     *
     * @param name bean 的名称
     * @param args 使用显式参数创建 bean 实例时使用的参数（仅在创建新实例而不是检索现有实例时应用）
     * @return bean 的一个实例
     * @throws BeansException 如果没有指定名称的 bean 定义
     *                        BeansException – 如果无法获得 bean
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 返回指定 bean 的一个实例
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException 如果没有指定名称的 bean 定义
     *                        BeansException – 如果无法获得 bean
     */
    <T> T getBean(String name, @Nullable Class<T> requiredType) throws BeansException;
}
