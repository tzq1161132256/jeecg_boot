package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Repayments;
import org.jeecg.modules.business.trade.mapper.RepaymentsMapper;
import org.jeecg.modules.business.trade.service.IRepaymentsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 金融项目还款信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class RepaymentsServiceImpl extends ServiceImpl<RepaymentsMapper, Repayments> implements IRepaymentsService {

}
