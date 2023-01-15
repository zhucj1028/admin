package cn.zhucj1028.aclservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: OSS服务类
 * @author zhucj
 * @date 2023/1/14 20:24
 * @version 1.0
 */
public interface OssService {
    /**
     * 上传头像到OSS
     *
     * @param file 图片文件
     * @return ok
     */
    String uploadFileAvatar(MultipartFile file);
}
