package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.InsuranceDetail;
import org.jeecg.modules.business.trade.mapper.InsuranceDetailMapper;
import org.jeecg.modules.business.trade.service.IInsuranceDetailService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 保险每期明细
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceDetailServiceImpl extends ServiceImpl<InsuranceDetailMapper, InsuranceDetail> implements IInsuranceDetailService {
    @Override
    public void processInsuranceDetail(InsuranceDetail insuranceDetail) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("period",insuranceDetail.getPeriod());
        queryWrapper.eq("orderno",insuranceDetail.getOrderno());
        InsuranceDetail insuranceDetail1 = getOne(queryWrapper,true);
        if(insuranceDetail1 == null){
            insuranceDetail.setCreateTime(new Date());
            insuranceDetail.setUpdateTime(new Date());
            insuranceDetail.setId(UUID.randomUUID().toString());
            save(insuranceDetail);
        }else {
            insuranceDetail.setId(insuranceDetail1.getId());
            insuranceDetail.setUpdateTime(new Date());
            updateById(insuranceDetail);
        }
    }
}
