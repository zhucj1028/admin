package cc.valuenet.wladmin.service;

import cc.valuenet.wladmin.model.Role;
import cc.valuenet.wladmin.util.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
public interface IRoleService extends IService<Role> {
    /**
     * 添加权限
     *
     * @param role
     */
    public void addRole(Role role);

    /**
     * 查询权限
     *
     * @param params
     * @return
     */
    PageUtils findByPage(Map<String, Object> params);

    /**
     * 根据ID获取角色
     *
     * @param id
     * @return
     */
    Role getRole(Integer id);

    /**
     * 根据角色对象进行修改
     *
     * @param role
     */
    void editById(Role role);


    /**
     * 通过用户id返回对应角色信息
     * @param id
     * @return
     */
    Role findByName(Integer id);
}
