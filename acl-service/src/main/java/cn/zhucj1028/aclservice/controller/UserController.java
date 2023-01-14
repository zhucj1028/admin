package cn.zhucj1028.aclservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.entity.*;
import cn.zhucj1028.aclservice.service.RoleService;
import cn.zhucj1028.aclservice.service.UserRoleService;
import cn.zhucj1028.aclservice.service.UserService;
import cn.zhucj1028.aclservice.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import cn.zhucj1028.aclservice.utils.R;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhucj
 * @version 1.0
 * @description: 用户控制器
 * @date 2023/1/11 20:16
 */
@RestController
@RequestMapping("/acl/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RedisUtils redisUtil;

    @Value("${avatarImagesFilePath}")
    private String avatarImagesFilePath;

    /**
     * 用户信息
     *
     * @param principal principal
     * @return map
     */
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R userInfo(Principal principal) {
        User currentUser = userService.getByUserName(principal.getName());
        return R.ok().put("currentUser", currentUser);
    }

    /**
     * 验证用户名
     *
     * @param user 用户名
     * @return success/error
     */
    @PostMapping("/checkUserName")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R checkUserName(@RequestBody User user) {
        if (userService.getByUserName(user.getUsername()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页
     *
     * @param pageBean 分页数据
     * @return map
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R list(@RequestBody PageBean pageBean) {
        System.out.println("pageBean:" + pageBean);
        String query = pageBean.getQuery().trim();
        // .ne("username","admin") 排除username=admin
        // Page<User> pageResult = userService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()), new QueryWrapper<User>().ne("username","admin").like(StringUtils.isNotEmpty(query), "username", query));
        Page<User> pageResult = userService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()), new QueryWrapper<User>().like(StringUtils.isNotEmpty(query), "username", query));
        List<User> userList = pageResult.getRecords();
        for (User user : userList) {
            // 获取角色
            List<Role> roleList = roleService.list(new QueryWrapper<Role>().inSql("id", "select role_id from acl_user_role where user_id=" + user.getId()));
            // 设置角色
            user.setSysRoleList(roleList);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userList", userList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 添加或者修改
     *
     * @param user 用户
     * @return ok
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:user:add')" + "||" + "hasAuthority('system:user:edit')")
    public R save(@RequestBody @Valid User user) {
        if (user.getId() == null || user.getId() == -1) {
            user.setCreateTime(new Date());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.save(user);
        } else {
            user.setUpdateTime(new Date());
            userService.updateById(user);
        }
        return R.ok();
    }

    /**
     * 修改密码
     *
     * @param user 用户
     * @return ok
     */
    @PostMapping("/updateUserPwd")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateUserPwd(@RequestBody User user) {
        User currentUser = userService.getById(user.getId());
        currentUser.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
        userService.updateById(currentUser);
        return R.ok();
    }

    /**
     * 更新status状态
     *
     * @param id id
     * @param status 状态
     * @return ok
     */
    @GetMapping("/updateStatus/{id}/status/{status}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateStatus(@PathVariable(value = "id") Integer id, @PathVariable(value = "status") String status) {
        User user = userService.getById(id);
        user.setStatus(status);
        userService.saveOrUpdate(user);
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return map
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        User user = userService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return R.ok(map);
    }

    /**
     * 重置密码
     *
     * @param id id
     * @return ok
     */
    @GetMapping("/resetPassword/{id}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R resetPassword(@PathVariable(value = "id") Integer id) {
        User user = userService.getById(id);
        user.setPassword(bCryptPasswordEncoder.encode(ConstantUtils.DEFAULT_PASSWORD));
        user.setUpdateTime(new Date());
        userService.updateById(user);
        return R.ok();
    }


    /**
     * 修改用户头像
     *
     * @param user 用户
     * @return ok
     */
    @RequestMapping("/updateAvatar")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateAvatar(@RequestBody User user) {
        User currentUser = userService.getById(user.getId());
        currentUser.setAvatar(user.getAvatar());
        userService.updateById(currentUser);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param ids id
     * @return ok
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:user:delete')")
    public R delete(@RequestBody Long[] ids) {
        userService.removeByIds(Arrays.asList(ids));
        userRoleService.remove(new QueryWrapper<UserRole>().in("user_id", ids));
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }

    /**
     * 用户角色授权
     *
     * @param userId 用户id
     * @param roleIds 角色id
     * @return ok
     */
    @Transactional
    @PostMapping("/grantRole/{userId}")
    @PreAuthorize("hasAuthority('system:user:role')")
    public R grantRole(@PathVariable("userId") Long userId, @RequestBody Long[] roleIds) {
        List<UserRole> userRoleList = new ArrayList<>();
        Arrays.stream(roleIds).forEach(r -> {
            UserRole sysUserRole = new UserRole();
            sysUserRole.setRoleId(r);
            sysUserRole.setUserId(userId);
            userRoleList.add(sysUserRole);
        });
        userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id", userId));
        userRoleService.saveBatch(userRoleList);
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }


}
