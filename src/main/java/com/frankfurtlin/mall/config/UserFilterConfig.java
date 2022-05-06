package com.frankfurtlin.mall.config;

import com.frankfurtlin.mall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/4 9:24
 */
@Configuration
public class UserFilterConfig {
    @Bean
    public UserFilter userFilter(){
        return new UserFilter();
    }

    @Bean(name = "userFilterConfiguration")
    public FilterRegistrationBean<UserFilter> userFilterConfig() {
        FilterRegistrationBean<UserFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(userFilter());
        filterRegistrationBean.addUrlPatterns("/mall/user/update");
        filterRegistrationBean.addUrlPatterns("/mall/user/password");
        filterRegistrationBean.addUrlPatterns("/mall/cart/*");
        filterRegistrationBean.addUrlPatterns("/mall/order/*");
        filterRegistrationBean.setName("userFilterConfig");
        return filterRegistrationBean;
    }
}
