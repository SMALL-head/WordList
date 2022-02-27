package com.zyc.wordlistsp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    /**
     * 分页拦截器的配置
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor paginationInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(true); //请求页面超过最大页，true跳回到首页
        paginationInnerInterceptor.setMaxLimit(500L); //每页最大数目，-1代表无限制
        paginationInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return paginationInterceptor;
    }
}
