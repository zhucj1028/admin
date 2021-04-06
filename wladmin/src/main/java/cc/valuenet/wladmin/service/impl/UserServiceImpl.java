package cc.valuenet.wladmin.service.impl;

import cc.valuenet.wladmin.mapper.UserMapper;
import cc.valuenet.wladmin.model.User;
import cc.valuenet.wladmin.model.UserRole;
import cc.valuenet.wladmin.service.IUserRoleService;
import cc.valuenet.wladmin.service.IUserService;
import cc.valuenet.wladmin.util.PageUtils;
import cc.valuenet.wladmin.util.Query;
import cc.valuenet.wladmin.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private IUserRoleService userRoleService;

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 查询用户列表
     * @param params
     * @return
     */
    @Override
    public PageUtils findByPage(Map<String, Object> params) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank((CharSequence) params.get("adminName")), User::getName, params.get("adminName"));
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 更新
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, user.getId());
        List<UserRole> userRoles = userRoleService.list(queryWrapper);
        if (userRoles != null && userRoles.size() > 0) {
            for (UserRole userRole : userRoles) {
                if (!userRole.getRid().equals(user.getRid())) {
                    userRoleService.remove(queryWrapper);
                    UserRole userRole1 = new UserRole();
                    userRole1.setUserId(user.getId());
                    userRole1.setRid(user.getRid());
                    userRoleService.save(userRole1);
                }
            }
        }
    }

    /**
     * 通过名称查询用户信息
     *
     * @param name
     * @return
     */
    @Override
    public User findByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getName, name);
       // queryWrapper.lambda().like(User::getName, name);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 通过手机号码查询角色信息
     *
     * @param user
     * @return
     */
    @Override
    public User findByPhone(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getPhone, user.getPhone());
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UserVo> selectLogin() {
        List<UserVo> login = userMapper.selectLogin();
        return login;
    }

    @Override
    public User findByLikeName(String name) {
        return userMapper.findByLikeName(name);
    }

}
