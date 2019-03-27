package com.fastjee.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author by wenzewoo on 2018/1/31.
 */
@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {"com.fastjee.common.web","com.fastjee.registration"})
public class RegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }
}
