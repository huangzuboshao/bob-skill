package com.bob.learn.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 父容器
 *
 * @author huangzuboshao
 * @date 2022/4/22 21:58
 */
@ComponentScan(value = {"com.bob.learn.mvc"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})})
public class RootConfig {
    public static void main(String[] args) {
        int count_bits = 32;
        System.out.println(-1 << 2);
        System.out.println(0 << 2);
        System.out.println(1 << 2);
        System.out.println(~1);
    }
}
