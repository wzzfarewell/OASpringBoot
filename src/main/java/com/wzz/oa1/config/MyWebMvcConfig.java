package com.wzz.oa1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    // 静态资源过滤设置 配置上传图片访问路径对应的绝对路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/employee/icon/**")
                .addResourceLocations("file:d:/Oa/UploadFiles/Icon/");
    }
}
