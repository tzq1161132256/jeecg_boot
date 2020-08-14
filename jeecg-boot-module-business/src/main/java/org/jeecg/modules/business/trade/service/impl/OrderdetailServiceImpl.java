package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Orderdetail;
import org.jeecg.modules.business.trade.mapper.OrderdetailMapper;
import org.jeecg.modules.business.trade.service.IOrderdetailService;
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
public class OrderdetailServiceImpl extends ServiceImpl<OrderdetailMapper, Orderdetail> implements IOrderdetailService {
	
	@Autowired
	private OrderdetailMapper orderdetailMapper;
	
	@Override
	public List<Orderdetail> selectByMainId(String mainId) {
		return orderdetailMapper.selectByMainId(mainId);
	}
}
