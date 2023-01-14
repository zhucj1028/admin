package cn.zhucj1028.aclservice.security;

import cn.hutool.json.JSONUtil;
import cn.zhucj1028.aclservice.utils.R;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description: 登录失败处理
 * @author zhucj
 * @date 2023/1/11 20:23
 * @version 1.0
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String message=e.getMessage();
        if(e instanceof BadCredentialsException){
            message="用户名或者密码错误！";
        }
        outputStream.write(JSONUtil.toJsonStr(R.error(message)).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();

    }
}
