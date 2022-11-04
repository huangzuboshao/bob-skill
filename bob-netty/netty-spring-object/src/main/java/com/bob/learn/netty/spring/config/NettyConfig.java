package com.bob.learn.netty.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author bob
 * @date 2022/10/11
 */
@ConfigurationProperties(prefix = NettyConfig.PREFIX)
@Component
@Data
public class NettyConfig {

    public static final String PREFIX = "netty";
    /**
     * 主机
     */
    private String host = "127.0.0.1";

    /**
     * 端口
     */
    private Integer port = 8080;

    /**
     * BB
     */
    private String additionalMetaData;

    @Deprecated
    @DeprecatedConfigurationProperty(reason = "host", replacement = "host")
    public String getAdditionalMetaData() {
        return additionalMetaData;
    }
}