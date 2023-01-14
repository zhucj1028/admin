package cn.zhucj1028.aclservice.controller;

import cn.zhucj1028.aclservice.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 测试
 * @author zhucj
 * @date 2023/1/11 20:16
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/demo")
    public R test(){
        System.out.println("===========11===========");
        return R.ok("这是测试").put("qqqq","wwwwwww");
    }

}
