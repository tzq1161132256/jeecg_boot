package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.business.trade.entity.*;
import org.jeecg.modules.business.trade.mapper.OrderdetailAllMapper;
import org.jeecg.modules.business.trade.mapper.OrderAllMapper;
import org.jeecg.modules.business.trade.service.IOrderAllService;
import org.jeecg.modules.business.trade.service.IOrderChronicdiseaseService;
import org.jeecg.modules.business.trade.service.IOrderService;
import org.jeecg.modules.business.trade.vo.OrderPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class OrderAllServiceImpl extends ServiceImpl<OrderAllMapper, OrderAll> implements IOrderAllService {

	@Autowired
	private OrderAllMapper orderAllMapper;
	@Autowired
	private OrderdetailAllMapper orderdetailAllMapper;

	@Autowired
	IOrderService orderService;

	@Autowired
	IOrderChronicdiseaseService orderChronicdiseaseService;

	@Override
	@Transactional
	public void saveMain(OrderAll orderAll, List<OrderdetailAll> orderdetailAllList) {
		orderAllMapper.insert(orderAll);
		for(OrderdetailAll entity:orderdetailAllList) {
			//外键设置
			entity.setOrderid(orderAll.getId());
			entity.setCreateTime(orderAll.getCreateTime());
			entity.setUpdateTime(orderAll.getUpdateTime());
			orderdetailAllMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(OrderAll orderAll,List<OrderdetailAll> orderdetailAllList) {
		orderAllMapper.updateById(orderAll);
		
		//1.先删除子表数据
		orderdetailAllMapper.deleteByMainId(orderAll.getId());
		
		//2.子表数据重新插入
		for(OrderdetailAll entity:orderdetailAllList) {
			//外键设置
			entity.setOrderid(orderAll.getId());
			entity.setCreateTime(orderAll.getCreateTime());
			entity.setUpdateTime(orderAll.getUpdateTime());
			orderdetailAllMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		orderdetailAllMapper.deleteByMainId(id);
		orderAllMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			orderdetailAllMapper.deleteByMainId(id.toString());
			orderAllMapper.deleteById(id);
		}
	}
	@Override
	@Transactional
	public void processOrderAll(OrderPage orderPage) throws Exception{

		if("慢病无忧".equals(orderPage.getProjectname()) && StringUtils.isBlank(orderPage.getId())) {

			OrderChronicdisease orderChronicdisease = new OrderChronicdisease();
			BeanUtils.copyProperties(orderPage, orderChronicdisease);
			orderChronicdisease.setId(orderPage.getOrderid());
			orderChronicdisease.setCreateTime(new Date());
			orderChronicdisease.setUpdateTime(new Date());
			orderChronicdiseaseService.saveOrUpdate(orderChronicdisease);
			return;
		}

		if("PBM（药康付）".equals(orderPage.getProjectname())) {
			// if( order.getOrderdate() )
			// 怡可安的时间
			String beginTime_yka = "2019-06-28 00:00:00";

			// 达伯舒的时间
			String beginTime_dbs = "2019-05-01 00:00:00";

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date date2 = format.parse(beginTime_yka);
			Date date_dbs = format.parse(beginTime_dbs);
			long beginMillisecond_yka = date2.getTime();
			long beginMillisecond_dbs = date_dbs.getTime();
			// PBM中商品是怡可安的数据，从2019年6月28日起，项目归属到 默克怡可安（怡可安心）
			if(orderPage.getOrderdate().getTime() >=  beginMillisecond_yka  &&  orderPage.getOrderdetailList() != null && orderPage.getOrderdetailList().size() >0 &&  "怡可安".equals(orderPage.getOrderdetailList().get(0).getMedname()) ) {
				orderPage.setRemark3(orderPage.getProjectname());
				orderPage.setProjectname("默克怡可安（怡可安心）");

			}

			// 达伯舒的数据，2019年5月1日之前，项目归属到 达伯舒
			if(orderPage.getOrderdate().getTime() <  beginMillisecond_dbs &&  orderPage.getOrderdetailList() != null && orderPage.getOrderdetailList().size() >0 && "达伯舒".equals(orderPage.getOrderdetailList().get(0).getMedname()) ) {
				orderPage.setRemark3(orderPage.getProjectname());
				orderPage.setProjectname("达伯舒");

			}

		}


		OrderAll orderAll = new OrderAll();
		BeanUtils.copyProperties(orderPage, orderAll);
		List<OrderdetailAll> orderdetailAllList = new ArrayList<OrderdetailAll>();
		for(Orderdetail orderdetail : orderPage.getOrderdetailList()) {
			OrderdetailAll orderdetailAll = new OrderdetailAll();
			BeanUtils.copyProperties(orderdetail, orderdetailAll);
			orderdetailAllList.add(orderdetailAll);
		}

/*		orderAll.setCreateTime(new Date());
		orderAll.setUpdateTime(new Date());
		saveMain(orderAll,orderdetailAllList);*/

		/**  根据订单编码+项目名称，判断是否存在。
		* */
		QueryWrapper<OrderAll> wrapper = new QueryWrapper<>();
		wrapper.eq("ordercode",orderAll.getOrdercode());
		wrapper.eq("projectname",orderAll.getProjectname());
		OrderAll orderAll_tmp = getOne(wrapper,true);
		if(orderAll_tmp == null) {
			orderAll.setCreateTime(new Date());
			orderAll.setUpdateTime(new Date());
			saveMain(orderAll,orderdetailAllList);
		} else {
			orderAll.setCreateTime(orderAll_tmp.getCreateTime());
			orderAll.setUpdateTime(new Date());
			orderAll.setId(orderAll_tmp.getId());
			updateMain(orderAll,orderdetailAllList);
		}

		// 如果是完成状态的订单，插入订单正式表
		if(orderAll.getProjectname().contains("金融")) {
			if ("已发货".equals(orderAll.getStatus()) || "已完成".equals(orderAll.getStatus()) || "放款成功,待发货".equals(orderAll.getStatus()) || "完成".equals(orderAll.getStatus())  ) {
				Order order = new Order();
				BeanUtils.copyProperties(orderAll, order);
				orderService.processOrder(order, orderPage.getOrderdetailList());
			} else {
				orderService.delMain(orderAll.getId());
			}
		} else {

			if ("完成".equals(orderAll.getStatus())
					|| "01".equals(orderAll.getStatus())
					|| "已完成".equals(orderAll.getStatus())
					|| "付款成功,待发货".equals(orderAll.getStatus())
					|| "已发货,待收货".equals(orderAll.getStatus())
					|| "已付款,待取药".equals(orderAll.getStatus())
					|| "支付成功,待第三方确认".equals(orderAll.getStatus())
					|| "第三方确认成功,待发货" .equals(orderAll.getStatus())
					|| "药房已发货".equals(orderAll.getStatus())
					||"已核销".equals(orderAll.getStatus())
					) {
				Order order = new Order();
				BeanUtils.copyProperties(orderAll, order);
				orderService.processOrder(order, orderPage.getOrderdetailList());
			} else {
				orderService.delMain(orderAll.getId());
			}
		}
	}

	@Override
	@Transactional
	public void updateOrderAll4Prescription(OrderAll orderAll) throws Exception {
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("ordercode",orderAll.getOrdercode());
	//	updateWrapper.eq("projectname",orderAll.getProjectname());
		update(orderAll,updateWrapper);
	}
}
