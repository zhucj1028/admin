package cc.valuenet.wladmin.controller;


import cc.valuenet.wladmin.model.Permission;
import cc.valuenet.wladmin.model.Role;
import cc.valuenet.wladmin.model.RolePermission;
import cc.valuenet.wladmin.service.IPermissionService;
import cc.valuenet.wladmin.service.IRolePermissionService;
import cc.valuenet.wladmin.service.IRoleService;
import cc.valuenet.wladmin.util.MyLog;
import cc.valuenet.wladmin.util.PageUtils;
import cc.valuenet.wladmin.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;
    @Resource
    private IRolePermissionService rolePermissionService;

    /**
     * 查询角色列表
     * @param params
     * @return
     */
    @MyLog(value = "查询用户列表")
    @RequestMapping("/findByPage")
    @ResponseBody
    public R findByPage(@RequestParam Map<String, Object> params) {
        PageUtils page = roleService.findByPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 返回所有角色
     * @param params
     * @return
     */
    @MyLog(value = "返回所有角色")
    @RequestMapping("/findAll")
    @ResponseBody
    public R findAll(@RequestParam Map<String, Object> params) {
        List<Role> list = roleService.list();
        return R.ok().put("data", list);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @MyLog(value = "新增角色")
    @PostMapping
    @ResponseBody
    public R save(@RequestBody Role role) {
        roleService.addRole(role);
        return R.ok();
    }

    /**
     * 查询角色详细
     * @param id
     * @return
     */
    @MyLog(value = "查看角色详情")
    @RequestMapping(value = {"/{id}", "/"})
    @ResponseBody
    public R getInfo(@PathVariable(value = "id", required = false) Integer id) {
        if (id == null) {
            return R.ok();
        }
        Role role = roleService.getRole(id);
        return R.ok().put("data", role);
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @MyLog(value = "修改角色")
    @PutMapping
    @ResponseBody
    public R update(@RequestBody Role role) {
        roleService.editById(role);
        return R.ok();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @MyLog(value = "删除角色")
    @DeleteMapping("{id}")
    @ResponseBody
    public R remove(@PathVariable Integer id) {
        roleService.removeById(id);
        return R.ok();
    }

    /**
     * 获取角色对应的资源以及所有的资源
     *
     * @param id
     * @return
     */
    @MyLog(value = "获取角色对应的资源")
    @RequestMapping(value = {"getRes/{id}"})
    @ResponseBody
    public R getRes(@PathVariable(value = "id", required = false) Integer id) {
        if (id == null) {
            return R.ok();
        }
        List<RolePermission> rolePermissionList = rolePermissionService.findByRid(id);
        List<Integer> permissionIdListByRole = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissionList) {
            permissionIdListByRole.add(rolePermission.getPermissionId());
        }
        List<Permission> permissionList = permissionService.findAll();
        return R.ok().put("data", permissionList).put("resIdListByRole", permissionIdListByRole);
    }

    /**
     * 修改角色
     * @param rolePermissionList
     * @return
     */
    @MyLog(value = "修改角色")
    @PostMapping("/impower")
    @ResponseBody
    public R impower(@RequestBody List<RolePermission> rolePermissionList) {
        rolePermissionService.removeRolePermission(rolePermissionList.get(0).getRid());
        rolePermissionService.removeById(rolePermissionList.get(0).getRid());
        rolePermissionService.saveBatch(rolePermissionList);
        return R.ok();
    }
}
