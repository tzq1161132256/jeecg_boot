package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.OrderdetailAll;
import org.jeecg.modules.business.trade.mapper.OrderdetailAllMapper;
import org.jeecg.modules.business.trade.service.IOrderdetailAllService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class OrderdetailAllServiceImpl extends ServiceImpl<OrderdetailAllMapper, OrderdetailAll> implements IOrderdetailAllService {
	
	@Autowired
	private OrderdetailAllMapper orderdetailAllMapper;
	
	@Override
	public List<OrderdetailAll> selectByMainId(String mainId) {
		return orderdetailAllMapper.selectByMainId(mainId);
	}
}
