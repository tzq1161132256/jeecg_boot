package org.jeecg.modules.business.insurance.service;

import org.jeecg.modules.business.insurance.entity.InsuranceHuaxia;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 华夏保险
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IInsuranceHuaxiaService extends IService<InsuranceHuaxia> {
   public void processInsuraceHuaxia(InsuranceHuaxia insuranceHuaxia) throws Exception;
}
