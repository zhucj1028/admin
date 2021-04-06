package cc.valuenet.wladmin.service.impl;

import cc.valuenet.wladmin.controller.PermissionController;
import cc.valuenet.wladmin.mapper.PermissionMapper;
import cc.valuenet.wladmin.model.Permission;
import cc.valuenet.wladmin.model.Role;
import cc.valuenet.wladmin.service.IPermissionService;
import cc.valuenet.wladmin.model.RolePermission;
import cc.valuenet.wladmin.service.IRolePermissionService;
import cc.valuenet.wladmin.util.PageUtils;
import cc.valuenet.wladmin.util.Query;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private IRolePermissionService rolePermissionService;

    @Override
    public void addPermission(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public PageUtils findByPage(Map<String, Object> params) {
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank((CharSequence) params.get("resName")), Permission::getName, params.get("name"));
        IPage<Permission> page = this.page(
                new Query<Permission>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Override
    public Permission getPermission(Integer id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Permission::getId, id);
        Permission Permission = permissionMapper.selectOne(queryWrapper);
        return Permission;
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectList(null);
    }

    /**
     * 通过角色查找对应资源
     *
     * @param role
     * @return
     */
    @Override
    public List<Permission> findByRole(Role role) {
        List<RolePermission> rolePermissionList = rolePermissionService.findByRid(role.getId());
        List<Integer> resList = rolePermissionList.stream().map(rolePermission -> rolePermission.getPermissionId()).collect(Collectors.toList());
        return baseMapper.selectBatchIds(resList);
    }
}
