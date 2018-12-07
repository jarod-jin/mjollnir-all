package com.dahua.tech.easywork.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.dahua.tech.easywork.api")
@EnableTransactionManagement
public class EasyworkPlatformApplication {

    public static void main(String args[]) {
        SpringApplication.run(EasyworkPlatformApplication.class, args);
    }



}
