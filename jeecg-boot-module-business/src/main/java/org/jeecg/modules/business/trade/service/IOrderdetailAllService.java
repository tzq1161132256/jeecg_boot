package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.OrderdetailAll;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
public interface IOrderdetailAllService extends IService<OrderdetailAll> {

	public List<OrderdetailAll> selectByMainId(String mainId);
}
