package cn.zhucj1028.aclservice.service;

import java.util.Map;

/**
 * @description: 短信服务类
 * @author zhucj
 * @date 2023/1/14 20:24
 * @version 1.0
 */
public interface MsmService {
    /**
     * 发送短信的方法
     */
    boolean send(Map<String, Object> param, String phone);
}
