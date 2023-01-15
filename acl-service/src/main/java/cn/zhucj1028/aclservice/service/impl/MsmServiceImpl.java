package cn.zhucj1028.aclservice.service.impl;

import cn.zhucj1028.aclservice.service.MsmService;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description: 短信实现类
 * @author zhucj
 * @date 2023/1/14 20:24
 * @version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {
    /**发送短信的方法
     * @param param 验证码
     * @param phone 手机号
     * @return ok
     */
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) {
            return false;
        }

        DefaultProfile profile = DefaultProfile.getProfile("default", ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName",ConstantUtils.SIGN_NAME); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode",ConstantUtils.TEMPLATE_CODE); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
