package com.fastjee.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author by wenzewoo on 2018/1/31.
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.fastjee.common.web","com.fastjee.config"})
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
