package cn.zhucj1028.aclservice.security;

import cn.hutool.json.JSONUtil;
import cn.zhucj1028.aclservice.utils.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description: JWT认证异常处理
 * @author zhucj
 * @date 2023/1/11 20:22
 * @version 1.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(JSONUtil.toJsonStr(R.error(HttpServletResponse.SC_UNAUTHORIZED,"认证失败，请登录！")).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
