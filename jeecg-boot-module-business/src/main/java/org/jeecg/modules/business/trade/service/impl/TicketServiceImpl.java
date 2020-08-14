package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Ticket;
import org.jeecg.modules.business.trade.mapper.TicketMapper;
import org.jeecg.modules.business.trade.service.ITicketService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 早鸟券申请信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements ITicketService {

}
