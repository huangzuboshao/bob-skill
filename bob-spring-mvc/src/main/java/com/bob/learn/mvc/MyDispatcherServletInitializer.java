package com.bob.learn.mvc;

import com.bob.learn.mvc.config.AppConfig;
import com.bob.learn.mvc.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * $
 *
 * @author huangzuboshao
 * @date 2022/4/22 21:59
 */
public class MyDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取根容器的配置类
     * 父容器
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("MyDispatcherServletInitializer..getRootConfigClasses");
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类
     * 子容器
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("MyDispatcherServletInitializer..getServletConfigClasses");
        return new Class<?>[]{AppConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     *
     * / 不包含jsp
     * /
     * /* 包含jsp
     *
     */
    @Override
    protected String[] getServletMappings() {
        System.out.println("MyDispatcherServletInitializer..getServletMappings");
        return new String[]{"/"};
    }
}
