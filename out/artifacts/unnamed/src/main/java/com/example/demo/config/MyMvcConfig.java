package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    /**
     * 添加视图控制器
     *
     * @param registry
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        System.out.println("come in ");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main").setViewName("dashboard");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 添加自定义的区域解析器
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    /**
     * 添加自定义的拦截器LoginHandlerInterceptor
     * 指定要拦截和放行的资源
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/", "/index.html", "/login", "/css/*", "/img/*", "/js/*", "/emp/*");
    }
}