package cc.valuenet.wladmin.controller;


import cc.valuenet.wladmin.model.Log;
import cc.valuenet.wladmin.model.User;
import cc.valuenet.wladmin.model.UserRole;
import cc.valuenet.wladmin.service.IUserRoleService;
import cc.valuenet.wladmin.service.IUserService;
import cc.valuenet.wladmin.util.MyLog;
import cc.valuenet.wladmin.util.PageUtils;
import cc.valuenet.wladmin.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    private IUserService userService;

    @Resource
    public IUserRoleService userRoleService;

    /**
     * 查询用户列表
     * http://localhost:8082/valuenet/api/user/findByPage
     *
     * @param params
     * @return
     */
    @MyLog(value = "返回用户列表")
    @RequestMapping("/findByPage")
    @ResponseBody
    public R findByPage(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.findByPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @MyLog(value = "新增用户")
    @PostMapping
    @ResponseBody
    public R save(@RequestBody User user) {
        userService.addUser(user);
        UserRole adminRole = new UserRole();
        adminRole.setUserId(user.getId());
        if (user.getRid() != null) {
            adminRole.setRid(user.getRid());
        } else {
            adminRole.setRid(2);
        }
        userRoleService.save(adminRole);
        return R.ok();
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @MyLog(value = "查询用户详情")
    @RequestMapping(value = {"/{id}", "/"})
    @ResponseBody
    public R getInfo(@PathVariable(value = "id", required = false) Integer id) {
        if (id == null) {
            return R.ok();
        }
        User user = userService.getUserById(id);
        return R.ok().put("data", user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @MyLog(value = "修改用户")
    @PutMapping
    @ResponseBody
    public R update(@RequestBody User user) {
        userService.updateUser(user);
        return R.ok();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @MyLog(value = "删除用户")
    @DeleteMapping("{id}")
    @ResponseBody
    public R remove(@PathVariable Integer id) {
        userService.removeById(id);
        return R.ok();
    }

    /**
     * 返回登陆方式
     * @return
     */
    @MyLog(value = "查看登录方式")
    @GetMapping("/logins")
    public R selectLogin() {
        List user = userService.selectLogin();
        return R.ok().put("data",user);
    }
}
