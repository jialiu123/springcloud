package com.fastjee.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author by wenzewoo on 2018/2/3.
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
    "com.fastjee.common.web","com.fastjee.usercenter","com.fastjee.db.mybatis"
})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }
}
