package cn.zhucj1028.aclservice.controller;

import cn.zhucj1028.aclservice.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.entity.*;
import cn.zhucj1028.aclservice.service.RolePermissionService;
import cn.zhucj1028.aclservice.service.RoleService;
import cn.zhucj1028.aclservice.service.UserRoleService;
import cn.zhucj1028.aclservice.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhucj
 * @version 1.0
 * @description: 角色控制器
 * @date 2023/1/11 20:16
 */
@RestController
@RequestMapping("/acl/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RedisUtils redisUtil;

    /**
     * 返回所有数据
     *
     * @return map
     */
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R listAll() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Role> roleList = roleService.list();
        resultMap.put("roleList", roleList);
        return R.ok(resultMap);
    }

    /**
     * 根据条件分页查询角色列表
     *
     * @param pageBean 分页数据
     * @return map
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R list(@RequestBody PageBean pageBean) {
        System.out.println("pageBean:" + pageBean);
        String query = pageBean.getQuery().trim();
        Page<Role> pageResult = roleService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()), new QueryWrapper<Role>().like(StringUtils.isNotEmpty(query), "name", query));
        List<Role> roleList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleList", roleList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 添加或者修改
     *
     * @param role 角色
     * @return ok
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:role:add')" + "||" + "hasAuthority('system:role:edit')")
    public R save(@RequestBody @Valid Role role) {
        if (role.getId() == null || role.getId() == -1) {
            role.setCreateTime(new Date());
            roleService.save(role);
        } else {
            role.setUpdateTime(new Date());
            roleService.updateById(role);
        }
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
    @PreAuthorize("hasAuthority('system:role:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        Role role = roleService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("role", role);
        return R.ok(map);
    }

    /**
     * 删除
     *
     * @param ids id
     * @return ok
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:role:delete')")
    public R delete(@RequestBody Long[] ids) {
        roleService.removeByIds(Arrays.asList(ids));
        userRoleService.remove(new QueryWrapper<UserRole>().in("role_id", ids));
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }

    /**
     * 获取当前角色的权限菜单
     *
     * @param id id
     * @return map
     */
    @GetMapping("/menus/{id}")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R menus(@PathVariable(value = "id") Integer id) {
        List<RolePermission> roleMenuList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id", id));
        List<Long> menuIdList = roleMenuList.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
        return R.ok().put("menuIdList", menuIdList);
    }

    /**
     * 更新角色权限信息
     *
     * @param id id
     * @param menuIds 菜单id
     * @return ok
     */
    @Transactional
    @PostMapping("/updateMenus/{id}")
    @PreAuthorize("hasAuthority('system:role:edit')")
    public R updateMenus(@PathVariable(value = "id") Long id, @RequestBody Long[] menuIds) {
        List<RolePermission> sysRoleMenuList = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId -> {
            RolePermission roleMenu = new RolePermission();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(id);
            sysRoleMenuList.add(roleMenu);
        });
        rolePermissionService.remove(new QueryWrapper<RolePermission>().eq("role_id", id));
        rolePermissionService.saveBatch(sysRoleMenuList);
        redisUtil.removeByPrex(ConstantUtils.AUTHORITY_KEY);
        return R.ok();
    }


}
