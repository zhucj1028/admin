package cn.zhucj1028.aclservice.service.impl;

import cn.zhucj1028.aclservice.service.OssService;
import cn.zhucj1028.aclservice.utils.ConstantUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @description: OSS实现类
 * @author zhucj
 * @date 2023/1/14 20:24
 * @version 1.0
 */
@Service
public class OssServiceImpl implements OssService {

    /**
     * 上传头像到OSS
     *
     * @param file 图片文件
     * @return 上传后的文件地址
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        try {
            //创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(ConstantUtils.END_POIND, ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //1在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //sadadeqwq.jpg
            fileName = uuid + fileName;
            //2 把文件按照日期进行分类
            //获取当前日期
            //2022/12/12
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //2022/12/12/sadadeqwq.jpg
            fileName = datePath + "/" + fileName;
            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(ConstantUtils.BUCKET_NAME, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://blog-moon.oss-cn-chengdu.aliyuncs.com/01.jpg
            return "https://" + ConstantUtils.BUCKET_NAME + "." + ConstantUtils.END_POIND + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
