package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Insurance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 疗效保险
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
public interface IInsuranceService extends IService<Insurance> {
 public void processInsurance(Insurance insurance) throws Exception;
}
