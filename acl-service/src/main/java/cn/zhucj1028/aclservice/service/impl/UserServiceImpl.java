package cn.zhucj1028.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.entity.Permission;
import cn.zhucj1028.aclservice.entity.Role;
import cn.zhucj1028.aclservice.entity.User;
import cn.zhucj1028.aclservice.mapper.PermissionMapper;
import cn.zhucj1028.aclservice.mapper.RoleMapper;
import cn.zhucj1028.aclservice.service.UserService;
import cn.zhucj1028.aclservice.mapper.UserMapper;
import cn.zhucj1028.aclservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 用户实现类
 * @author zhucj
 * @date 2023/1/11 21:01
 * @version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtils redisUtil;

    @Override
    public User getByUserName(String username) {
        return getOne(new QueryWrapper<User>().eq("username",username));
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {
        StringBuffer authority=new StringBuffer();

        if(redisUtil.hasKey(ConstantUtils.AUTHORITY_KEY+userId)){
            authority.append(redisUtil.get(ConstantUtils.AUTHORITY_KEY,String.valueOf(userId)));
        }else{
            // 获取角色
            List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>().inSql("id", "select role_id from acl_user_role where user_id=" + userId));

            if(roleList.size()>0){
                String roleCodeStrs=roleList.stream().map(r->"ROLE_"+r.getCode()).collect(Collectors.joining(","));
                authority.append(roleCodeStrs);
            }
            // 获取菜单权限
            Set<String> menuCodeSet = new HashSet<String>();
            for(Role sysRole:roleList){
                List<Permission> sysMenuList = permissionMapper.selectList(new QueryWrapper<Permission>().inSql("id", "select menu_id from acl_role_permission where role_id=" + sysRole.getId()));
                for(Permission sysMenu:sysMenuList){
                    String perms=sysMenu.getPerms();
                    if(StringUtils.isNotEmpty(perms)){
                        menuCodeSet.add(perms);
                    }
                }
            }
            if(menuCodeSet.size()>0){
                authority.append(",");
                String menuCodeStrs = menuCodeSet.stream().collect(Collectors.joining(","));
                authority.append(menuCodeStrs);
            }
            redisUtil.set(ConstantUtils.AUTHORITY_KEY,String.valueOf(userId),authority,10*60);
            System.out.println("authority:"+authority.toString());
        }
        return authority.toString();
    }
}




