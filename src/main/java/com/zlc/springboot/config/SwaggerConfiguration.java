package com.zlc.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2   //开启Swagger 它底层有默认的一些配置
public class SwaggerConfiguration {

    //配置了Swagger的Docket的bean实例. 使用自己定制的ui界面
    @Bean
    public Docket docket(Environment environment) {

        //设置要显示的Swagger环境,(要求,生产环境使用Swagger.发布环境不使用Swagger)
        Profiles profiles = Profiles.of("dev");
        //获取项目的当前环境. 不符合Profile要求则关闭Swagger文档功能.
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //自定义分组名称
                .groupName("hello")
                //是否开启Swagger
                .enable(flag)
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式.
                // basePackage指定要扫描的包.
                // any()扫描全部
                // none() 不扫描
                // withClassAnnotation(注解的class):扫描类上的注解.根据注解的class
//                .apis(RequestHandlerSelectors.basePackage("com.zlc.springboot.controller"))
                .apis(RequestHandlerSelectors.basePackage("com.zlc.springboot"))
                // paths过滤请求地址,不生成接口文档.0
                // .paths(PathSelectors.ant("/zlc/**"))
                .build()
                ;
    }

    //配置Swagger信息apiinfo;
    private ApiInfo apiInfo() {

        //作者信息
        Contact contact = new Contact("张先生", "htttp://www.baidu.com", "1633079383@qq.com");

        return new ApiInfo(
                "张..的接口文档.",
                "即使再小的帆也能远航",
                "1.0",
                "http://www.baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
