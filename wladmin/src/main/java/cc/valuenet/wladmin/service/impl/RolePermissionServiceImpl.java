package cc.valuenet.wladmin.service.impl;

import cc.valuenet.wladmin.mapper.RolePermissionMapper;
import cc.valuenet.wladmin.model.RolePermission;
import cc.valuenet.wladmin.service.IRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void addRolePermission(RolePermission rolePermission) {
        rolePermissionMapper.insert(rolePermission);
    }

    @Override
    public List<RolePermission> findByRid(Integer rid) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RolePermission::getRid, rid);
        return rolePermissionMapper.selectList(queryWrapper);
    }

    /**
     * 通过角色id删除角色菜单关联
     *
     * @param rid 角色id
     */
    @Override
    public void removeRolePermission(Integer rid) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RolePermission::getRid, rid);
        baseMapper.delete(queryWrapper);
    }
}
