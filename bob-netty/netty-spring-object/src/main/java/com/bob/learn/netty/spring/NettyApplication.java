package com.bob.learn.netty.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author bob
 * @date 2022/10/10
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
public class NettyApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class,args);
    }
    @Override
    public void run(String... args) throws Exception {

    }
}
