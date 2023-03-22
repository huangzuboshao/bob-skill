package com.bob.learn.springframework.beans;

import com.sun.istack.internal.Nullable;

/**
 * @author Bob
 * @date 2022/7/14 18:02
 */
public interface PropertyValues {

    /**
     * 返回此对象中保存的 PropertyValue 对象的数组。
     * @return
     */
    PropertyValue[] getPropertyValues();

    /**
     * 返回具有给定名称的属性值（如果有）。
     *
     * @param propertyName 要搜索的名称
     * @return 属性值，如果没有则为null
     */
    @Nullable
    PropertyValue getPropertyValue(String propertyName);

    /**
     * 添加属性
     * @param pv 属性
     */
    void addPropertyValue(PropertyValue pv);
}
