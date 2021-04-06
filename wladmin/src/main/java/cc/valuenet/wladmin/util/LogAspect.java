package cc.valuenet.wladmin.util;

import cc.valuenet.wladmin.model.Log;
import cc.valuenet.wladmin.service.ILogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author Zcj
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private ILogService logService;

    /***
     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
    @Pointcut("@annotation(cc.valuenet.wladmin.util.MyLog)")
    public void controllerAspect() {}

    //切面 配置通知
    @AfterReturning("controllerAspect()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");

        Log sysLog = new Log();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            //保存获取的操作
            sysLog.setOperation(value);
        }
        sysLog.setCreateTime(LocalDateTime.now());
        //调用service保存SysLog实体类到数据库
        logService.save(sysLog);
    }


}
