package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.OrderChronicdisease;
import org.jeecg.modules.business.trade.mapper.OrderChronicdiseaseMapper;
import org.jeecg.modules.business.trade.service.IOrderChronicdiseaseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 慢病订单
 * @Author: jeecg-boot
 * @Date:   2019-08-02
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class OrderChronicdiseaseServiceImpl extends ServiceImpl<OrderChronicdiseaseMapper, OrderChronicdisease> implements IOrderChronicdiseaseService {

}
