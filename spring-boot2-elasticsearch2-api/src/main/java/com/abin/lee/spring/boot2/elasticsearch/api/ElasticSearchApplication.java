package com.abin.lee.spring.boot2.elasticsearch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by abin on 2019/7/13.
 */
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)   //开启事物管理功能
public class ElasticSearchApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    Spring Boot Application Boot SUCCESS   ヾ(◍°∇°◍)ﾉﾞ\n" +
                " ______                    _   ______            \n" +
                "|_   _ \\                  / |_|_   _ `.          \n" +
                "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
                "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
                " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
                "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(ElasticSearchApplication.class);
    }


}