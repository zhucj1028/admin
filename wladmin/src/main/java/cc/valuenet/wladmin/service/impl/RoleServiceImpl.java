package cc.valuenet.wladmin.service.impl;

import cc.valuenet.wladmin.mapper.RoleMapper;
import cc.valuenet.wladmin.model.Role;
import cc.valuenet.wladmin.model.UserRole;
import cc.valuenet.wladmin.service.IRoleService;
import cc.valuenet.wladmin.service.IUserRoleService;
import cc.valuenet.wladmin.util.PageUtils;
import cc.valuenet.wladmin.util.Query;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private IUserRoleService userRoleService;

    /**
     * 添加权限
     * @param role
     */
    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    /**
     * 查询权限
     * @param params
     * @return
     */
    @Override
    public PageUtils findByPage(Map<String, Object> params) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank((CharSequence) params.get("roleName")),Role::getName, params.get("roleName"));
        IPage<Role> page = this.page(
                new Query<Role>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    /**
     * 根据ID获取角色
     * @param id
     * @return
     */
    @Override
    public Role getRole(Integer id) {
        return roleMapper.selectById(id);
    }

    /**
     * 根据角色对象进行修改
     * @param role
     */
    @Override
    public void editById(Role role) {
        roleMapper.updateById(role);
    }

    /**
     * 通过用户id返回对应角色信息
     *
     * @param id
     * @return
     */
    @Override
    public Role findByName(Integer id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, id);
        UserRole userRole = userRoleService.getOne(queryWrapper);
        return baseMapper.selectById(userRole.getRid());
    }
}
