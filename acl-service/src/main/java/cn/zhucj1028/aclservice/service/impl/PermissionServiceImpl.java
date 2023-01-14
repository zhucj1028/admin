package cn.zhucj1028.aclservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zhucj1028.aclservice.entity.Permission;
import cn.zhucj1028.aclservice.service.PermissionService;
import cn.zhucj1028.aclservice.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 权限实现类
 * @author zhucj
 * @date 2023/1/11 20:24
 * @version 1.0
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {


    /**
     * 构造菜单树
     * @param sysMenuList
     * @return
     */
     public List<Permission> buildTreeMenu(List<Permission> sysMenuList){
        List<Permission> resultMenuList = new ArrayList<>();

        for (Permission sysMenu : sysMenuList) {
            // 寻找子节点
            for (Permission e : sysMenuList) {
                if (e.getParentId()==sysMenu.getId()) {
                    sysMenu.getChildren().add(e);
                }
            }
            // 判断父节点，添加到集合
            if(sysMenu.getParentId()==0L){
                resultMenuList.add(sysMenu);
            }
        }
        return resultMenuList;
    }

}




