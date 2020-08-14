package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.jeecg.modules.business.trade.entity.Order;
import org.jeecg.modules.business.trade.entity.OrderAll;
import org.jeecg.modules.business.trade.entity.Orderdetail;
import org.jeecg.modules.business.trade.entity.OrderdetailAll;
import org.jeecg.modules.business.trade.mapper.OrderdetailMapper;
import org.jeecg.modules.business.trade.mapper.OrderMapper;
import org.jeecg.modules.business.trade.service.IOrderAllService;
import org.jeecg.modules.business.trade.service.IOrderService;
import org.jeecg.modules.business.trade.vo.OrderPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.*;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderdetailMapper orderdetailMapper;

	@Override
	@Transactional
	public void saveMain(Order order, List<Orderdetail> orderdetailList) {
		orderMapper.insert(order);
		for(Orderdetail entity:orderdetailList) {
			//外键设置
			entity.setOrderid(order.getId());
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			orderdetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(Order order,List<Orderdetail> orderdetailList) {

		orderMapper.updateById(order);
		
		//1.先删除子表数据
		orderdetailMapper.deleteByMainId(order.getId());
		
		//2.子表数据重新插入
		for(Orderdetail entity:orderdetailList) {
			//外键设置
			entity.setOrderid(order.getId());
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			orderdetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		orderdetailMapper.deleteByMainId(id);
		orderMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			orderdetailMapper.deleteByMainId(id.toString());
			orderMapper.deleteById(id);
		}
	}

	@Override
	public void processOrder(Order order,List<Orderdetail> orderdetailList) throws Exception {

		// 订单的支付时间为空的，用订单日期代替
		if(order.getSettlementdate() == null ) {
			order.setSettlementdate(order.getOrderdate());
		}

		// 购买人信息为空的，用患者信息代替
		/**  根据订单编码+项目名称，判断是否存在。
		 * */
		QueryWrapper<Order> wrapper = new QueryWrapper<>();
		wrapper.eq("ordercode",order.getOrdercode());
		wrapper.eq("projectname",order.getProjectname());
		Order order_tmp = getOne(wrapper,true);
		if(order_tmp == null) {
			order.setCreateTime(new Date());
			order.setUpdateTime(new Date());
			saveMain(order,orderdetailList);
		} else {
			order.setCreateTime(order_tmp.getCreateTime());
			order.setUpdateTime(new Date());
			order.setId(order_tmp.getId());
			updateMain(order,orderdetailList);
		}
	}

	@Override
	public void updateOrder4Prescription(Order order) throws Exception {
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("ordercode",order.getOrdercode());
		// updateWrapper.eq("projectname",order.getProjectname());
		update(order,updateWrapper);

	}

	@Override
	public List<Map> getLimitOrder(Map<String, Object> var1) {
		return orderMapper.getLimitOrder(var1);
	}

	@Override
	public List<Map> getLimitOrder4marketing(Map<String, Object> var1) {
		return orderMapper.getLimitOrder4marketing(var1);
	}
}
