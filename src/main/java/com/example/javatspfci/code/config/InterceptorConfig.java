package com.example.javatspfci.code.config;

import com.example.javatspfci.code.intereptor.AdminInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        String[] pathPatterns = {"/**"};

        String[] excludePathPatterns = {"/base/**"};



    }
}
