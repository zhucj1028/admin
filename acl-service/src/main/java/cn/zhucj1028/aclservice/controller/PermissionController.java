package cn.zhucj1028.aclservice.controller;

import cn.zhucj1028.aclservice.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.entity.Permission;
import cn.zhucj1028.aclservice.service.PermissionService;
import cn.zhucj1028.aclservice.service.RoleService;
import cn.zhucj1028.aclservice.service.UserService;
import cn.zhucj1028.aclservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author zhucj
 * @version 1.0
 * @description: 菜单控制器
 * @date 2023/1/11 20:15
 */
@RestController
@RequestMapping("/acl/menu")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RedisUtils redisUtil;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('system:menu:query')")
    public R list() {
        // 查询所有菜单信息
        List<Permission> menuList = permissionService.list(new QueryWrapper<Permission>().orderByAsc("order_num"));
        return R.ok().put("treeMenu", permissionService.buildTreeMenu(menuList));
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return map
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:menu:query')")
    public R findById(@PathVariable(value = "id") Long id) {
        Permission permission = permissionService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("permission", permission);
        return R.ok(map);
    }

    /**
     * 添加或者修改
     *
     * @param permission
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:menu:add')" + "||" + "hasAuthority('system:menu:edit')")
    public R save(@RequestBody @Valid Permission permission) {
        if (permission.getId() == null || permission.getId() == -1) {
            permission.setCreateTime(new Date());
            permissionService.save(permission);
        } else {
            permission.setUpdateTime(new Date());
            permissionService.updateById(permission);
        }
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }


    /**
     * 删除
     *
     * @param id 菜单ID
     * @return ok
     */
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    public R delete(@PathVariable(value = "id") Long id) {
        int count = permissionService.count(new QueryWrapper<Permission>().eq("parent_id", id));
        if (count > 0) {
            return R.error("请先删除子菜单！");
        }
        permissionService.removeById(id);
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }


}
