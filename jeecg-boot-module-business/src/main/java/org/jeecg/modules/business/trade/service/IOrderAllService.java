package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Order;
import org.jeecg.modules.business.trade.entity.OrderdetailAll;
import org.jeecg.modules.business.trade.entity.OrderAll;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.business.trade.vo.OrderPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
public interface IOrderAllService extends IService<OrderAll> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(OrderAll orderAll, List<OrderdetailAll> orderdetailAllList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(OrderAll orderAll, List<OrderdetailAll> orderdetailAllList);
	
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
	public void processOrderAll(OrderPage orderPage) throws Exception;

	/**
	 *
	 * 更新订单的处方信息
	 */
	public void updateOrderAll4Prescription(OrderAll orderAll) throws Exception;
}
