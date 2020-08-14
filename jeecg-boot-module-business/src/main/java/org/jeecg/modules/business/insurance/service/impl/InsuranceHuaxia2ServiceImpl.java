package org.jeecg.modules.business.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.insurance.entity.InsuranceHuaxia2;
import org.jeecg.modules.business.insurance.mapper.InsuranceHuaxia2Mapper;
import org.jeecg.modules.business.insurance.service.IInsuranceHuaxia2Service;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 华夏保险升级版
 * @Author: jeecg-boot
 * @Date:   2019-08-27
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceHuaxia2ServiceImpl extends ServiceImpl<InsuranceHuaxia2Mapper, InsuranceHuaxia2> implements IInsuranceHuaxia2Service {

}
