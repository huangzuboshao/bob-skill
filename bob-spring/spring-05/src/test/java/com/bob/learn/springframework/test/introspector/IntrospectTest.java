package com.bob.learn.springframework.test.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * java内省
 *
 * @author Bob
 * @date 2022/8/4 10:04
 */
class People{
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void m1() {
        System.out.println("m1"+name);
    }
}
public class IntrospectTest<T> {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        People people = new People();
        BeanInfo beanInfo = Introspector.getBeanInfo(people.getClass());
        //Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
        //System.out.println(beanInfo.getBeanDescriptor());
        PropertyDescriptor pd = new PropertyDescriptor("name",people.getClass());
        pd.getWriteMethod().invoke(people, "aaa");
        people.m1();

        Arrays.stream(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
