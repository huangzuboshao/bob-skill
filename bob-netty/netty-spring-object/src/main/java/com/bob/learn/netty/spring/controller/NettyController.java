package com.bob.learn.netty.spring.controller;

import com.bob.learn.netty.spring.config.NettyConfig;
import com.bob.learn.netty.spring.config.NettyServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author bob
 * @date 2022/10/10
 */
@RestController
@RequestMapping(value = "netty-server",method = RequestMethod.GET)
public class NettyController {
    @Resource
    private NettyServer nettyServer;

    @Resource
    private NettyConfig nettyConfig;

    @RequestMapping("/localAddress")
    public String localAddress() {
        return "nettyServer localAddress " + nettyServer.getChannel().localAddress();
    }

    @RequestMapping("/isOpen")
    public String isOpen() {
        return "nettyServer isOpen " + nettyServer.getChannel().isOpen();
    }

    @RequestMapping("/test")
    public String test() {
        return nettyConfig.getAdditionalMetaData();
    }
}
