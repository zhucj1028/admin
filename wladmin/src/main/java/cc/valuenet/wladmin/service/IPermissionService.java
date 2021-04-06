package cc.valuenet.wladmin.service;

import cc.valuenet.wladmin.model.Permission;
import cc.valuenet.wladmin.model.Role;
import cc.valuenet.wladmin.util.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 添加资源
     * @param permission
     */
    public void addPermission(Permission permission);
    /**
     * 查询资源
     * @param params
     * @return
     */
    PageUtils findByPage(Map<String, Object> params);

    /**
     * 根据ID获取资源
     * @param id
     * @return
     */
    Permission getPermission(Integer id);

    /**
     * 查询所有资源信息
     * @return
     */
    List<Permission> findAll();

    /**
     * 通过角色查找对应资源
     * @param role
     * @return
     */
    List<Permission> findByRole(Role role);
}
