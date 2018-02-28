package com.naver.homefood;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.naver.homefood.interceptor.LoginCheckInterceptor;
import com.naver.homefood.interceptor.LoginInterceptor;
import com.naver.homefood.interceptor.LogoutInterceptor;
import com.naver.homefood.interceptor.SellerCheckInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${file.image.load-path}")
    private String imageLoadPath;

    @Value("${message.not_auth}")
    private String notAuth;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(notAuth)).addPathPatterns("/board/**").addPathPatterns("/image/**")
            .addPathPatterns("/food/**").addPathPatterns("/order/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/signInAction");
        registry.addInterceptor(new LogoutInterceptor()).addPathPatterns("/logout");
        registry.addInterceptor(new SellerCheckInterceptor(notAuth)).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/boardImage/**").addResourceLocations(imageLoadPath);
    }

}
