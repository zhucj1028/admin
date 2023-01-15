package cn.zhucj1028.aclservice.config;

import cn.zhucj1028.aclservice.security.*;
import cn.zhucj1028.aclservice.service.impl.MyUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: SpringSecurity配置
 * @author zhucj
 * @date 2023/1/11 20:12
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private CaptchaFilter captchaFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Autowired
    MyUserDetailServiceImpl myUserDetailService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    // 请求白名单
    private static final String[] URL_WHITELIST ={
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/image/**",
            "/test/demo",
            "/bk/info/**",
            "/msm/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .csrf()
        .disable() // 跨域 csrf攻击关闭

        // 登录配置
        .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)

        .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)


        // 禁用session
        .and()
            .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // 配置拦截规则
        .and()
                .authorizeRequests()
                        .antMatchers(URL_WHITELIST).permitAll()
                        .anyRequest().authenticated()

        // 异常处理器
        .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)


        // 配置自定义过滤器
        .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }
}
