package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Bill;
import org.jeecg.modules.business.trade.mapper.BillMapper;
import org.jeecg.modules.business.trade.service.IBillService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 慢病无忧对账单
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

}
