package cn.zhucj1028.aclservice.security;

import cn.hutool.json.JSONUtil;
import cn.zhucj1028.aclservice.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import cn.zhucj1028.aclservice.entity.Permission;
import cn.zhucj1028.aclservice.entity.Role;
import cn.zhucj1028.aclservice.entity.User;
import cn.zhucj1028.aclservice.service.PermissionService;
import cn.zhucj1028.aclservice.service.RoleService;
import cn.zhucj1028.aclservice.service.UserService;
import cn.zhucj1028.aclservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 登录成功处理
 * @author zhucj
 * @date 2023/1/11 20:23
 * @version 1.0
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService sysUserService;

    @Autowired
    private RoleService sysRoleService;

    @Autowired
    private PermissionService sysMenuService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        // 获取用户名
        String username=authentication.getName();
        // 更新最后登录日期
        sysUserService.update(new UpdateWrapper<User>().set("login_date",new Date()).eq("username",username));

        // 生成jwt token
        String token=JwtUtils.genJwtToken(username);
        User currentUser = sysUserService.getByUserName(username);

        // 获取当前用户拥有的权限菜单
        // 获取角色
        List<Role> roleList = sysRoleService.list(new QueryWrapper<Role>().inSql("id", "select role_id from acl_user_role where user_id=" + currentUser.getId()));

        // 设置角色
        currentUser.setRoles(roleList.stream().map(Role::getName).collect(Collectors.joining(",")));
        StringBuilder permsStr=new StringBuilder();

        // 获取菜单权限
        Set<Permission> menuSet = new HashSet<Permission>();
        for(Role sysRole:roleList){
            List<Permission> sysMenuList = sysMenuService.list(new QueryWrapper<Permission>().inSql("id", "select menu_id from acl_role_permission where role_id=" + sysRole.getId()));
            for(Permission sysMenu:sysMenuList){
                menuSet.add(sysMenu);
                permsStr.append(sysMenu.getPerms()).append(",");
            }
        }
        String[] perms = StringUtils.tokenizeToStringArray(permsStr.toString(),",");
        //转成集合List
        List<Permission> sysMenuList = new ArrayList<>(menuSet);
        //排序
        sysMenuList.sort(Comparator.comparing(Permission::getOrderNum));
        //构造菜单树
        List<Permission> menuList = sysMenuService.buildTreeMenu(sysMenuList);
        outputStream.write(JSONUtil.toJsonStr(R.ok("登录成功").put("authorization",token).put("menuList",menuList).put("currentUser",currentUser).put("perms",perms)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }



}
