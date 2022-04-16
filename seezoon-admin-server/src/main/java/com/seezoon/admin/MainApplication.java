package com.seezoon.admin;

import com.seezoon.admin.infrastructure.properties.DictProperties;
import com.seezoon.mybatis.repository.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan(basePackages = "com.seezoon.admin.domain.**.mapper", markerInterface = BaseMapper.class)
@SpringBootApplication
@EnableConfigurationProperties({DictProperties.class})
public class MainApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
