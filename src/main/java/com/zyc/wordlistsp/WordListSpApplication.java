package com.zyc.wordlistsp;

import com.zyc.wordlistsp.config.PageConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.zyc.wordlistsp.mapper")
@EnableCaching
public class WordListSpApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WordListSpApplication.class, args);
        PageConfig pageConfig = run.getBean("pageConfig", PageConfig.class);
//        System.out.println(pageConfig.getLimitation());
    }

}
