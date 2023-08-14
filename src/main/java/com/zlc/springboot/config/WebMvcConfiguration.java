package com.zlc.springboot.config;

//import com.zlc.springboot.handler.AllHandller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMvc自动配置
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //试图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/show/store");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/logto.html").setViewName("logto");
        registry.addViewController("/store.html").setViewName("/show/store");
        registry.addViewController("/dosoft.html").setViewName("/show/dosoft");
        registry.addViewController("/server.html").setViewName("/show/server");
        registry.addViewController("/showStroe.html").setViewName("/show/showStroe");
        registry.addViewController("/upload.html").setViewName("/show/upload");

        //后台界面路径映射
        registry.addViewController("/ht_index.html").setViewName("/ht/index");
        registry.addViewController("/user_chart.html").setViewName("/ht/user_chart");
        registry.addViewController("/user_show.html").setViewName("/ht/user_show");
        registry.addViewController("/user_update.html").setViewName("/ht/user_update");

    }

    //    //资源拦截
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/templates/");

        registry.addResourceHandler("/store/image/**").addResourceLocations("file:D:/mogu/");

    }

    //设置拦截网站请
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //配置拦截路径
//        registry.addInterceptor(new AllHandller()).addPathPatterns("/**")
//                //配置放行路径
//                .excludePathPatterns("/",
//                        "/alipay/return_url",
//                        "/toDown/**",
//                        "/A-to-fileUpload**",
//                        "/login"
////                        ,"/wxPayDown/**"
//                        , "/isUser","/getImage", "/toLogTo", "/user/register", "/A-to-Login", "/A-to-store", "/A-to-Share", "/index.html",
//                        "/login.html", "/logon.html","/Soft-All","/Soft-Boutique","/Soft-ManyCount","/Soft-Free","/Soft-All","/A-to-store","/A-to-Logto",
//                        "/logto.html", "/user/register", "http://whois.pconline.com.cn", "/store/image/**","/css/**", "/js/**", "/img/**",
//                        "/A-to-dosoft","/Soft-fileUpload/**","/orderquery/**","/createNative/**","/toWx/**","/toPay/**","/A-to-server","/need-submit","/store/image/**","/A-to-showStore/**","/Soft-SelectOne/**");
//    }
}
