package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Userregistration;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户注册信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IUserregistrationService extends IService<Userregistration> {
public void processUserregistration(Userregistration userregistration) throws Exception;
}
