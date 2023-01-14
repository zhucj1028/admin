package cn.zhucj1028.aclservice.exceptionhandler;

import cn.zhucj1028.aclservice.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 全局异常处理
 * @author zhucj
 * @date 2023/1/11 20:21
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException e){
        return R.error(e.getMessage());
    }
}
