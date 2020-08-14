package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Orderdetail;
import org.jeecg.modules.business.trade.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.business.trade.vo.OrderPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
public interface IOrderService extends IService<Order> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Order order, List<Orderdetail> orderdetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Order order, List<Orderdetail> orderdetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);

	/**
	 *
	 * 处理订单
	 */
	public void processOrder(Order order,List<Orderdetail> orderdetailList) throws Exception;

	/**
	 *
	 * 更新订单的处方信息
	 */
	public void updateOrder4Prescription(Order order) throws Exception;


	List<Map> getLimitOrder(Map<String, Object> var1);

	List<Map> getLimitOrder4marketing(Map<String, Object> var1);
	
}
