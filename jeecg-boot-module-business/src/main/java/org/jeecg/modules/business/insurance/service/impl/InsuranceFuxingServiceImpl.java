package org.jeecg.modules.business.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.insurance.entity.InsuranceFuxing;
import org.jeecg.modules.business.insurance.mapper.InsuranceFuxingMapper;
import org.jeecg.modules.business.insurance.service.IInsuranceFuxingService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 复星保险
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceFuxingServiceImpl extends ServiceImpl<InsuranceFuxingMapper, InsuranceFuxing> implements IInsuranceFuxingService {

}
