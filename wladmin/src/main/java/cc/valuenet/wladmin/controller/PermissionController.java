package cc.valuenet.wladmin.controller;


import cc.valuenet.wladmin.model.Permission;
import cc.valuenet.wladmin.service.IPermissionService;
import cc.valuenet.wladmin.util.MyLog;
import cc.valuenet.wladmin.util.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Resource(name = "permissionServiceImpl")
    private IPermissionService permissionService;

    /**
     * 查询菜单列表
     * @return
     */
    @MyLog(value = "查询菜单列表")
    @RequestMapping("/findAll")
    @ResponseBody
    public R findAll() {
        List<Permission> permissionList = permissionService.list();
        return R.ok().put("data", permissionList);
    }

    @MyLog(value = "删除用户")
    @RequestMapping("/findByRole")
    public R findByRole(HttpServletRequest request) {
        String role = request.getHeader("X-ROLE");
        System.out.println("role = " + role);
        return null;
    }

    /**
     * 添加菜单
     * @param permissiones
     * @return
     */
    @MyLog(value = "添加菜单")
    @PostMapping
    @ResponseBody
    public R save(@RequestBody Permission permissiones) {
        permissionService.addPermission(permissiones);
        return R.ok();
    }

    /**
     * 根据角色ID查询菜单下拉树结构
     * @param id
     * @return
     */
    @MyLog(value = "查询角色下菜单")
    @DeleteMapping("/{resId}")
    public R remove(@PathVariable("resId") Integer id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getParentId, id);
        List<Permission> list = permissionService.list(queryWrapper);
        if (list.size() == 0) {
            permissionService.removeById(id);
            return R.ok();
        }else {
            return R.error("该菜单下还有子菜单，删除失败");
        }
    }

    /**
     * 查询菜单详细
     * @param id
     * @return
     */
    @MyLog(value = "查询菜单详情")
    @RequestMapping("/{id}")
    @ResponseBody
    public R getInfo(@PathVariable Integer id) {
        System.out.println("ResAction.getInfo");
        Permission permission = permissionService.getPermission(id);
        System.out.println(permission.getName()+"<<<<<<<<<<<<<<<<<<<");
        return R.ok().put("data", permission);
    }

    /**
     * 更新菜单
     * @param permission
     * @return
     */
    @MyLog(value = "跟新菜单")
    @PutMapping()
    public R update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok();
    }
}
