package cc.valuenet.wladmin.mapper;

import cc.valuenet.wladmin.model.User;
import cc.valuenet.wladmin.model.User;
import cc.valuenet.wladmin.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户登录方式
     * @return
     */
    List<UserVo> selectLogin();

    public User findByLikeName(String name);
}
