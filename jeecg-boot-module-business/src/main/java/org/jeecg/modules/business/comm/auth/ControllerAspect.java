package org.jeecg.modules.business.comm.auth;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jeecg.common.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect //该注解标示该类为切面类
@Component //注入依赖
public class ControllerAspect {
    private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    @Autowired
    private SiganatureInterceptor siganatureInterceptor;
    /**
     * 对Controller进行安全和身份校验
     */
    @Around("within(@org.springframework.stereotype.Controller *) && @annotation(is)")
    public Object validIdentityAndSecure(ProceedingJoinPoint pjp, SecureValid is)
            throws Exception {
        Object[] args = pjp.getArgs();
        //Controller中所有方法的参数，前两个分别为：Request,Response
        HttpServletRequest request = (HttpServletRequest) args[0];
        // HttpServletResponse response = (HttpServletResponse)args[1];


        // 对appid和signature进行校验
        try {
            AppSiganatureCredentials credentials = siganatureInterceptor.validateCredentials(request);
            args[2] = credentials;
        } catch (Exception e) {

            return Result.error(e.getMessage());
        }
        // 继续执行接下来的代码
        Object retVal = null;
        try {
            retVal = pjp.proceed(args);
        } catch (Throwable e) {
            if (e instanceof Exception) { return Result.error(e.getMessage()); }
        }
        // 目前的接口走不到这里
        return retVal;
    }
}