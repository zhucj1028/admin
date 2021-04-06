package cc.valuenet.wladmin.service;

import cc.valuenet.wladmin.model.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
public interface IRolePermissionService extends IService<RolePermission> {
    /**
     *
     * @param rolePermission
     */
    public void addRolePermission(RolePermission rolePermission);

    /**
     * 根据角色ID获取数据
     * @param rId
     * @return
     */
    public List<RolePermission> findByRid(Integer rId);


    /**
     * 通过角色id删除角色菜单关联
     * @param rid 角色id
     */
    void removeRolePermission(Integer rid);
}
