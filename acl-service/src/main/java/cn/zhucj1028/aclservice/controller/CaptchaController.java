package cn.zhucj1028.aclservice.controller;


import cn.zhucj1028.aclservice.utils.R;
import com.google.code.kaptcha.Producer;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import cn.zhucj1028.aclservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 验证码控制器
 * @author zhucj
 * @date 2023/1/11 20:15
 * @version 1.0
 */
@RestController
public class CaptchaController {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtil;

    @GetMapping("/captcha")
    public R captcha() throws IOException {
        // 生成随机唯一key
        String key= UUID.randomUUID().toString();
        String code = producer.createText();

        System.out.println("code="+code);

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset(ConstantUtils.CAPTCHA_KEY,key,code,60*5);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("base64Img",base64Img);
        resultMap.put("uuid",key);
        return R.ok(resultMap);

    }

}
