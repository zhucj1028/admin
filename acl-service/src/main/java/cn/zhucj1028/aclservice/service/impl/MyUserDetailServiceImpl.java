package cn.zhucj1028.aclservice.service.impl;

import cn.zhucj1028.aclservice.exceptionhandler.BookingException;
import cn.zhucj1028.aclservice.entity.User;
import cn.zhucj1028.aclservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: SpringSecurity实现登录类
 * @author zhucj
 * @date 2023/1/11 20:24
 * @version 1.0
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sysUser=userService.getByUserName(username);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户名不存在！");
        }else if("1".equals(sysUser.getStatus())){
            throw new BookingException("该用户账号已被封禁，具体联系管理员！");
        }
        return new org.springframework.security.core.userdetails.User(sysUser.getUsername(),sysUser.getPassword(),getUserAuthority(sysUser.getId()));
    }

    /**
     * 获取用户权限信息 包括角色 菜单权限信息
     * @param userId 用户id
     * @return 用户信息
     */
    public List<GrantedAuthority> getUserAuthority(Long userId){
        String authority=userService.getUserAuthorityInfo(userId);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
