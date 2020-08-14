package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Postransaction;
import org.jeecg.modules.business.trade.mapper.PostransactionMapper;
import org.jeecg.modules.business.trade.service.IPostransactionService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: pos机交易信息
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class PostransactionServiceImpl extends ServiceImpl<PostransactionMapper, Postransaction> implements IPostransactionService {
    @Override
    public void processPostransaction(Postransaction postransaction) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("terminalcode",postransaction.getTerminalcode());
        queryWrapper.eq("transactiontime",postransaction.getTransactiontime());

        Postransaction postransaction1 = getOne(queryWrapper,true);
        if(postransaction1 == null ) {
            postransaction.setUpdateTime(new Date());
            postransaction.setCreateTime(new Date());
            postransaction.setDatelastupdate(new Date());
        //    postransaction.setCreateTime(new Date());
            postransaction.setId(UUID.randomUUID().toString());
            save(postransaction);
        }else {
            postransaction.setId(postransaction1.getId());
            postransaction.setUpdateTime(new Date());
            postransaction.setDatelastupdate(new Date());
            updateById(postransaction);
        }
    }
}
