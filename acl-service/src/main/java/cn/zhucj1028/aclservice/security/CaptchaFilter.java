package cn.zhucj1028.aclservice.security;


import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.exceptionhandler.BookingException;
import cn.zhucj1028.aclservice.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 验证码过滤器
 * @author zhucj
 * @date 2023/1/11 20:22
 * @version 1.0
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    RedisUtils redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String url = httpServletRequest.getRequestURI();
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
            try{
                // 校验验证码
                validate(httpServletRequest);
            } catch (BookingException e) {
                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("uuid");
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(key)) {
            throw new BookingException("验证码错误");
        }
        if (!code.equals(redisUtil.hget(ConstantUtils.CAPTCHA_KEY, key))) {
            throw new BookingException("验证码错误");
        }

    }
}
