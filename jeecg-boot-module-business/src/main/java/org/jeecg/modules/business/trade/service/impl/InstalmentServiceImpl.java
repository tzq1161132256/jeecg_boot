package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Instalment;
import org.jeecg.modules.business.trade.mapper.InstalmentMapper;
import org.jeecg.modules.business.trade.service.IInstalmentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 金融分期
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class InstalmentServiceImpl extends ServiceImpl<InstalmentMapper, Instalment> implements IInstalmentService {
    @Override
    public void processInstalment(Instalment instalment) throws Exception {
        if ("00".equals(instalment.getStatus())) {
            instalment.setStatus("申请中");
        }else if ("10".equals(instalment.getStatus())) {
            instalment.setStatus("资料已提交,待审核");
        }else if ("11".equals(instalment.getStatus())) {
            instalment.setStatus("审核不通过");
        }else if ("12".equals(instalment.getStatus())) {
            instalment.setStatus("补件,待审核");
        }else if ("13".equals(instalment.getStatus())) {
            instalment.setStatus("待用户补件");
        }else if ("14".equals(instalment.getStatus())) {
            instalment.setStatus("签名不通过,待重签");
        }else if ("20".equals(instalment.getStatus())) {
            instalment.setStatus("审核通过,待用户确认");
        }else if ("30".equals(instalment.getStatus())) {
            instalment.setStatus("审核通过");
        }else if ("99".equals(instalment.getStatus())) {
            instalment.setStatus("金融终止");
        }

        if(instalment.getApprovedate() == null ) {
            instalment.setApprovedate(instalment.getApplydate());
        }
        //  订单状态 00-申请中，10-资料已提交,待审核，11-审核不通过，12-补件,待审核，13-待用户补件，14-签名不通过,待重签，20-审核通过,待用户确认，30-审核通过，99-金融终止
        if( "审核通过".equals(instalment.getStatus()) || "金融终止".equals(instalment.getStatus()) || "审核通过,待用户确认".equals(instalment.getStatus())  )
        {
            instalment.setAuditfaildate(null);
            instalment.setAuditsuccessdate(instalment.getApprovedate());
        }else if( "审核不通过".equals(instalment.getStatus()) || "签名不通过,待重签".equals(instalment.getStatus()) /**|| "待用户补件".equals(instalment.getStatus())**/ ){
            instalment.setAuditfaildate(instalment.getApprovedate());
            instalment.setAuditsuccessdate(null);
        } else {
            instalment.setApprovedate(null);
            instalment.setAuditsuccessdate(null);
            instalment.setAuditfaildate(null);
        }
        instalment.setUpdateTime(new Date());
        instalment.setCreateTime(new Date());
        instalment.setId(instalment.getApplyid());
        saveOrUpdate(instalment);
    }
}
