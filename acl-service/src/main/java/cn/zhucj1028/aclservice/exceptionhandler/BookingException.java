package cn.zhucj1028.aclservice.exceptionhandler;

import org.springframework.security.core.AuthenticationException;

/**
 * @description: 统一处理异常类
 * @author zhucj
 * @date 2023/1/11 20:20
 * @version 1.0
 */
public class BookingException extends AuthenticationException {

    public BookingException(String msg, Throwable t) {
        super(msg, t);
    }

    public BookingException(String msg) {
        super(msg);
    }

}
