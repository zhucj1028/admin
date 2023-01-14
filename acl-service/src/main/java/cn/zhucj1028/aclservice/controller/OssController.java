package cn.zhucj1028.aclservice.controller;

import cn.zhucj1028.aclservice.service.OssService;
import cn.zhucj1028.aclservice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileOss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像的方法
     *
     * @param file 头像文件
     * @return 文件路径
     */
    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().put("url", url);
    }
}
