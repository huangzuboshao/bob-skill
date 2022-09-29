package com.bob.learn.springframework.test.interfac;

/**
 * @author Bob
 * @date 2022/8/11 11:18
 */
public class DefaultListableBeanFactory implements BeanFactory{
    @Override
    public Object getBean() {
        return "this is Bean";
    }
}
