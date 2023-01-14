package cn.zhucj1028.aclservice.service;

import java.util.Map;

public interface MsmService {
    /**
     * 发送短信的方法
     */
    boolean send(Map<String, Object> param, String phone);
}
