// package cc.valuenet.wladmin.iterceptor;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// /**
//  * @author Zcj
//  */
// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//
//     @Bean
//     LoginInterceptor loginInterceptor(){
//         return new LoginInterceptor();
//     }
//
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         //注入自定义拦截器 ，添加拦截路径和排除拦截路径
//
//         registry.addInterceptor(loginInterceptor())
//                 //拦截全部路径
//                 .addPathPatterns("/api/v1/pri/*/*/**")
//                 //不会被拦截的路径
//                 .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");//拦截除登陆以外的其他接口
//         WebMvcConfigurer.super.addInterceptors(registry);
//     }
// }
