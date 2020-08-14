package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.InsuranceClaims;
import org.jeecg.modules.business.trade.mapper.InsuranceClaimsMapper;
import org.jeecg.modules.business.trade.service.IInsuranceClaimsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 保险报销
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceClaimsServiceImpl extends ServiceImpl<InsuranceClaimsMapper, InsuranceClaims> implements IInsuranceClaimsService {

}
