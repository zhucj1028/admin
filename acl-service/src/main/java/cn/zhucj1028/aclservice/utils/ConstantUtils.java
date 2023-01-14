package cn.zhucj1028.aclservice.utils;


/**
 * @author zhucj
 * @version 1.0
 * @description: 通用常量类
 * @date 2023/1/11 20:24
 */
public class ConstantUtils {

    //captcha key
    public final static String CAPTCHA_KEY = "captcha";

    //authority key
    public final static String AUTHORITY_KEY = "authority";

    //重置密码
    public final static String DEFAULT_PASSWORD = "123456";

    //Token不存在
    public static final int JWT_ERRCODE_NULL = 4000;

    //Token过期
    public static final int JWT_ERRCODE_EXPIRE = 4001;

    //验证不通过
    public static final int JWT_ERRCODE_FAIL = 4002;

    //密匙
    public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d";

    public static final long JWT_TTL = 24 * 60 * 60 * 1000;

    //定义公开静态常量
    public static final String END_POIND = "oss-cn-chengdu.aliyuncs.com";
    public static final String ACCESS_KEY_ID = "";
    public static final String ACCESS_KEY_SECRET = "";
    public static final String BUCKET_NAME = "";
    public static final String SIGN_NAME = "总要抱点妄想";
    public static final String TEMPLATE_CODE = "";

}
