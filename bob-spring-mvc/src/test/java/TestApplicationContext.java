import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static org.springframework.core.io.support.ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;

/**
 * @author huangzuboshao
 * @date 2023/9/12 12:13
 */
@Slf4j
public class TestApplicationContext {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        //testApplicationContext01();
        //testClassPathXmlApplicationContext();
        //testAnnotationConfigApplicationContext();
        //testAnnotationConfigServletWebServerApplicationContext();
    }



    private static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    private static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    private static void testApplicationContext01() throws NoSuchFieldException, IllegalAccessException, IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Field field = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        field.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) field.get(beanFactory);
        map.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
        // 1.MessageSource 国际化
        System.out.println(applicationContext.getMessage("hi", null, Locale.CHINA));
        System.out.println(applicationContext.getMessage("hi", null, Locale.ENGLISH));
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        // 2.ResourcePatternResolver
        Resource[] resources = applicationContext.getResources(CLASSPATH_ALL_URL_PREFIX + "application.yml");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
        // 3.EnvironmentCapable
        System.out.println(applicationContext.getEnvironment().getProperty("java_home"));
        System.out.println(applicationContext.getEnvironment().getProperty("server.port"));
        //4. ApplicationEventPublisher 事件发布器
        applicationContext.publishEvent(null);
    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }
    }

    static class Bean1{

    }

    public static void testComment(String str, Integer i) {
        log.info("TestApplicationContext.testComment() parameters => 【str = {}】, 【i = {}】", str, i);
    }
}
