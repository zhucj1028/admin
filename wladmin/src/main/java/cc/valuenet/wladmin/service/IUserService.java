package cc.valuenet.wladmin.service;

import cc.valuenet.wladmin.model.User;
import cc.valuenet.wladmin.util.PageUtils;
import cc.valuenet.wladmin.vo.UserVo;
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
public interface IUserService extends IService<User> {
    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 查询用户
     * @param params
     * @return
     */
    PageUtils findByPage(Map<String, Object> params);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据id修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 通过名称查询用户信息
     * @return
     * @param name
     */
    User findByName(String name);


    /**
     * 通过手机号码查询角色信息
     * @param admin
     * @return
     */
    User findByPhone(User admin);

    /**
     * 查询用户登录方式
     * @return
     */
    List<UserVo> selectLogin();

    User findByLikeName(String name);
}
