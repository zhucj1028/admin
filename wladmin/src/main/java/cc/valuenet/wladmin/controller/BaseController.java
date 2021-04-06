package cc.valuenet.wladmin.controller;

import cc.valuenet.wladmin.model.Permission;
import cc.valuenet.wladmin.model.Role;
import cc.valuenet.wladmin.model.User;
import cc.valuenet.wladmin.service.IPermissionService;
import cc.valuenet.wladmin.service.IRoleService;
import cc.valuenet.wladmin.service.IUserService;
import cc.valuenet.wladmin.util.HttpUtil;
import cc.valuenet.wladmin.util.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zcj
 */
@RestController
public class BaseController {
    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IPermissionService resService;

    @RequestMapping("/test")
    public void test() {
        System.out.println("=============test===============");
    }

    @RequestMapping("/login")
    public R login(@RequestBody User user, HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        boolean device = HttpUtil.isMobileDevice(request);
        User loginUser = null;
        if (device) {
            loginUser = userService.findByPhone(user);
        } else {
            loginUser = userService.findByName(user.getName());
        }
        if (loginUser == null) {
            return R.error("用户名或密码不正确");
        }
        if (!loginUser.getPassword().equals(user.getPassword())) {
            return R.error("用户名或密码不正确");
        }

        Role role = roleService.findByName(loginUser.getId());

        List<Permission> resList = resService.findByRole(role);
        return R.ok().put("data", role.getId()).put("resList", resList).put("admin", loginUser);
    }

    @RequestMapping("/logout")
    public R logout() {

        return R.ok();
    }
}
