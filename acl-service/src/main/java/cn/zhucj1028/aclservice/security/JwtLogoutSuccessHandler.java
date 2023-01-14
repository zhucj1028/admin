package cn.zhucj1028.aclservice.security;

import cn.hutool.json.JSONUtil;
import cn.zhucj1028.aclservice.service.UserService;
import cn.zhucj1028.aclservice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * @description: 自定义退出处理
 * @author zhucj
 * @date 2023/1/11 20:23
 * @version 1.0
 */
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private UserService sysUserService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        outputStream.write(JSONUtil.toJsonStr(R.ok("退出成功")).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
