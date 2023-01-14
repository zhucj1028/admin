package cn.zhucj1028.aclservice.service;

import cn.zhucj1028.aclservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description: 用户服务类
 * @author zhucj
 * @date 2023/1/11 20:24
 * @version 1.0
 */
public interface UserService extends IService<User> {

    User getByUserName(String username);

    String getUserAuthorityInfo(Long userId);
}
