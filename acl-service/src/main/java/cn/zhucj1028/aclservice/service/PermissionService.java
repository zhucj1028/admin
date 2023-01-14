package cn.zhucj1028.aclservice.service;

import cn.zhucj1028.aclservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @description: 权限服务类
 * @author zhucj
 * @date 2023/1/11 20:24
 * @version 1.0
 */
public interface PermissionService extends IService<Permission> {

     public List<Permission> buildTreeMenu(List<Permission> sysMenuList);

}
