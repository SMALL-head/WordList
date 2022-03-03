package com.zyc.wordlistsp;

import com.zyc.wordlistsp.pojo.PageConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zyc.wordlistsp.mapper")
public class WordListSpApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WordListSpApplication.class, args);
        PageConfig pageConfig = run.getBean("pageConfig", PageConfig.class);
//        System.out.println(pageConfig.getLimitation());
    }

}
