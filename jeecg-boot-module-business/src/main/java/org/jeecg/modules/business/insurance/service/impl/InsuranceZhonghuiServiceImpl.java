package org.jeecg.modules.business.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.insurance.entity.InsuranceZhonghui;
import org.jeecg.modules.business.insurance.mapper.InsuranceZhonghuiMapper;
import org.jeecg.modules.business.insurance.service.IInsuranceZhonghuiService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 众惠保险
 * @Author: jeecg-boot
 * @Date:   2019-07-29
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceZhonghuiServiceImpl extends ServiceImpl<InsuranceZhonghuiMapper, InsuranceZhonghui> implements IInsuranceZhonghuiService {

}
