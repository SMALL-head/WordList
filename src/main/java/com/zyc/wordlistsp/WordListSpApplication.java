package com.zyc.wordlistsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.zyc.wordlistsp.mapper")
public class WordListSpApplication {

    public static void main(String[] args) {
       SpringApplication.run(WordListSpApplication.class, args);
    }

}
