package cc.valuenet.wladmin.controller;


import cc.valuenet.wladmin.mapper.LogMapper;
import cc.valuenet.wladmin.util.MyLog;
import cc.valuenet.wladmin.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wuqu
 * @since 2021-03-27
 */
@RestController
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    LogMapper logMapper;

    @MyLog(value = "查看日志")
    @GetMapping("selectLog")
    public R selectList() {
       List data =  logMapper.findLogs();
        return R.ok().put("data",data);
    }
}
