package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.InsuranceDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 保险每期明细
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IInsuranceDetailService extends IService<InsuranceDetail> {
   public void processInsuranceDetail(InsuranceDetail insuranceDetail) throws Exception;
}
