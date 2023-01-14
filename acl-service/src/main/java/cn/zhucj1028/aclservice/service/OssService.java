package cn.zhucj1028.aclservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    /**
     * 上传头像到OSS
     *
     * @param file 图片文件
     * @return ok
     */
    String uploadFileAvatar(MultipartFile file);
}
