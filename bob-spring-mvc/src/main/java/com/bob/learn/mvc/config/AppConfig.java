package com.bob.learn.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 子容器
 *
 * @author huangzuboshao
 * @date 2022/4/22 21:48
 */
@ComponentScan(value = {"com.bob.learn.mvc"},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})},
        //禁用默认过滤规则
        useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
}
