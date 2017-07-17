package com.hezhujun.shopping;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by hezhujun on 2017/6/1.
 * 应用上下文
 */
@Configuration
@ComponentScan(basePackages = "com.hezhujun.shopping",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
public class RootConfig {

}
