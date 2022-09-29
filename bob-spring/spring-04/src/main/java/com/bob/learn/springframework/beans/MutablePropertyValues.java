package com.bob.learn.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * bean属性集合包装
 * @author Bob
 * @date 2022/7/14 18:06
 */
public class MutablePropertyValues implements PropertyValues{

    private final List<PropertyValue> propertyValueList;

    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    @Override
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public MutablePropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }
}
