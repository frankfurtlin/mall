package com.frankfurtlin.mall.config;

import com.frankfurtlin.mall.filter.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/29 14:42
 */
@Configuration
public class AdminFilterConfig {
    @Bean
    public AdminFilter adminFilter(){
        return new AdminFilter();
    }

    @Bean(name = "adminFilterConfiguration")
    public FilterRegistrationBean<AdminFilter> adminFilterConfig() {
        FilterRegistrationBean<AdminFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(adminFilter());
        filterRegistrationBean.addUrlPatterns("/mall/category/admin/*");
        filterRegistrationBean.addUrlPatterns("/mall/product/admin/*");
        filterRegistrationBean.setName("adminFilterConfig");
        return filterRegistrationBean;
    }
}
