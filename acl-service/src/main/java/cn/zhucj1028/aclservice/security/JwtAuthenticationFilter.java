package cn.zhucj1028.aclservice.security;

import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.entity.CheckResult;
import cn.zhucj1028.aclservice.entity.User;
import cn.zhucj1028.aclservice.service.UserService;
import cn.zhucj1028.aclservice.service.impl.MyUserDetailServiceImpl;
import cn.zhucj1028.aclservice.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description: JWT过滤器
 * @author zhucj
 * @date 2023/1/11 20:23
 * @version 1.0
 */
public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    @Autowired
    private UserService sysUserService;

    // 请求白名单
    private static final String URL_WHITELIST[]={
            "/captcha"
    };

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        System.out.println("request.getRequestURI()"+request.getRequestURI());
        if(StringUtils.isEmpty(token) || new ArrayList<String>(Arrays.asList(URL_WHITELIST)).contains(request.getRequestURI())){
            chain.doFilter(request,response);
            return;
        }
        CheckResult checkResult = JwtUtils.validateJWT(token);
        if(!checkResult.isSuccess()){
            switch (checkResult.getErrCode()){
                case ConstantUtils.JWT_ERRCODE_NULL: throw new JwtException("Token不存在");
                case ConstantUtils.JWT_ERRCODE_FAIL: throw new JwtException("Token验证不通过");
                case ConstantUtils.JWT_ERRCODE_EXPIRE: throw new JwtException("Token过期");
            }
        }


        Claims claims = JwtUtils.parseJWT(token);
        String username = claims.getSubject();

        User sysUser = sysUserService.getByUserName(username);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,null,myUserDetailService.getUserAuthority(sysUser.getId()));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        chain.doFilter(request,response);

    }
}
