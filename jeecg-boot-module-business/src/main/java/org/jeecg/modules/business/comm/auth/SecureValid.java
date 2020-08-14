package org.jeecg.modules.business.comm.auth;

import java.lang.annotation.*;

/*
 * 校验签名合法性 自定义事务
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SecureValid {
    String desc() default "身份和安全验证开始...";
}