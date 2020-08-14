package org.jeecg.modules.business.insurance.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.insurance.entity.InsuranceHuaxia;
import org.jeecg.modules.business.insurance.mapper.InsuranceHuaxiaMapper;
import org.jeecg.modules.business.insurance.service.IInsuranceHuaxiaService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 华夏保险
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceHuaxiaServiceImpl extends ServiceImpl<InsuranceHuaxiaMapper, InsuranceHuaxia> implements IInsuranceHuaxiaService {
    @Override
    public void processInsuraceHuaxia(InsuranceHuaxia insuranceHuaxia) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("policyno",insuranceHuaxia.getPolicyno());
        InsuranceHuaxia insuranceHuaxia1 = getOne(queryWrapper,true);
        if(insuranceHuaxia1 == null ) {
            insuranceHuaxia.setId(UUID.randomUUID().toString());
            insuranceHuaxia.setCreateTime(new Date());
            insuranceHuaxia.setUpdateTime(new Date());
            save(insuranceHuaxia);
        }else {
            insuranceHuaxia.setId(insuranceHuaxia1.getId());
            insuranceHuaxia.setUpdateTime(new Date());
            updateById(insuranceHuaxia);
        }

    }
}
