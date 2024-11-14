package com.dark.api.config;

//import com.gxust.api.interceptor.AdminLoginInterceptor;
//
//import com.gxust.api.interceptor.PortalLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport{

//    @Resource
//    AdminLoginInterceptor adminLoginInterceptor;
//
//    @Resource
//    PortalLoginInterceptor portalLoginInterceptor;


//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(portalLoginInterceptor).addPathPatterns("/portal/**").excludePathPatterns("/portal/school/login");
//        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/user/login");
//    }



    @Bean
    public FormContentFilter createFormContentFilter(){
        return new FormContentFilter();
    }



    @Bean
    public Docket createDocketAdmin() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("暗黑奶茶店")
                .version("测试版：test_0.1.2")
                .description("校企合作大项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("管理端")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dark.api.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
//
//    @Bean
//    public Docket createDocketApp() {
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("校企合作大项目")
//                .version("测试版：test_0.1.2")
//                .description("校企合作大项目接口文档")
//                .build();
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo)
//                .groupName("学校端")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gxust.api.controller.portal"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
         * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
         * allowCredentials：是否开启Cookie
         * allowedMethods：允许的请求方式，如：POST、GET、PUT、DELETE等。
         * allowedOrigins：允许访问的url，可以固定单条或者多条内容
         * allowedHeaders：允许的请求header，可以自定义设置任意请求头信息。
         * maxAge：配置预检请求的有效时间
         */

        registry.addMapping("/**")
//                .allowedOrigins("http://127.0.0.1:8080","http://localhost:8080","http://localhost:8082","http://127.0.0.1:5173","http://localhost:5173","http://127.0.0.1:5174","http://localhost:5174")
//                .allowCredentials(true)  //cookie
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600);  //如果每次请求都加载了token, options
    }
}
