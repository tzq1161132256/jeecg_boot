package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Insurance;
import org.jeecg.modules.business.trade.mapper.InsuranceMapper;
import org.jeecg.modules.business.trade.service.IInsuranceService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 疗效保险
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InsuranceServiceImpl extends ServiceImpl<InsuranceMapper, Insurance> implements IInsuranceService {
    @Override
    public void processInsurance(Insurance insurance) throws Exception {
        //状态值转换
        if("00".equals(insurance.getStatus())) {
            insurance.setStatus("资料上报中");
        }else if ("10".equals(insurance.getStatus())){
            insurance.setStatus("资料待审核");
        }else if ("20".equals(insurance.getStatus())){
            insurance.setStatus("保险出单成功");
        }else if ("21".equals(insurance.getStatus())){
            insurance.setStatus("保险审核不通过");
        }else if ("11".equals(insurance.getStatus())){
            insurance.setStatus("资料审核失败");
        }else if ("15".equals(insurance.getStatus())){
            insurance.setStatus("资料审核成功");
        }


        if(insurance.getAuditdate() == null) {
            insurance.setAuditdate(insurance.getApplicationdate());
        }
        if ("资料审核失败".equals(insurance.getStatus()) || "资料审核不通过".equals(insurance.getStatus())|| "未通过".equals(insurance.getStatus()) || "保险审核不通过".equals(insurance.getStatus())) {
            // insurance.setAuditDate();
            insurance.setAuditfaildate(insurance.getAuditdate());
            insurance.setAuditsuccessdate(null);
        } else if ("资料审核成功".equals(insurance.getStatus()) || "已通过".equals(insurance.getStatus()) || "保险出单成功".equals(insurance.getStatus()) || "保单已生成".equals(insurance.getStatus()) ){
            insurance.setAuditfaildate(null);
            insurance.setAuditsuccessdate(insurance.getAuditdate());
        }  else {
            insurance.setAuditdate(null);
            insurance.setAuditsuccessdate(null);
            insurance.setAuditfaildate(null);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("orderno",insurance.getOrderno());
        queryWrapper.eq("projectname",insurance.getProjectname());
        Insurance insurance1 = getOne(queryWrapper,true);
        if (insurance1 == null){
            insurance.setCreateTime(new Date());
            insurance.setUpdateTime(new Date());
            insurance.setId(UUID.randomUUID().toString());
            save(insurance);
        }else{
            insurance.setId(insurance1.getId());
            insurance.setUpdateTime(new Date());
            updateById(insurance);
        }

    }
}
