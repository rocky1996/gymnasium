package com.acat.gymnasium;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAsync
@MapperScan(basePackages = "com.acat.gymnasium.dao")
@EnableJpaRepositories
public class GymnasiumApplication {
    public static void main(String[] args) {
        SpringApplication.run(GymnasiumApplication.class, args);
    }
}
