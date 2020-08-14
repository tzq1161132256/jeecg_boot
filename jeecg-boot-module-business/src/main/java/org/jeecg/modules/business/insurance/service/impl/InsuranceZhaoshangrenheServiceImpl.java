package org.jeecg.modules.business.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.insurance.entity.InsuranceZhaoshangrenhe;
import org.jeecg.modules.business.insurance.mapper.InsuranceZhaoshangrenheMapper;
import org.jeecg.modules.business.insurance.service.IInsuranceZhaoshangrenheService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 招商仁和保险
 * @Author: jeecg-boot
 * @Date:   2019-07-29
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceZhaoshangrenheServiceImpl extends ServiceImpl<InsuranceZhaoshangrenheMapper, InsuranceZhaoshangrenhe> implements IInsuranceZhaoshangrenheService {

}
