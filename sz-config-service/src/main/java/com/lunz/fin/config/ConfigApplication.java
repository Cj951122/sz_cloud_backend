package com.lunz.fin.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author admin
 * @apiNote 配置中心启动类
 * @date 2020-02-14
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.lunz.fin")
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

}
