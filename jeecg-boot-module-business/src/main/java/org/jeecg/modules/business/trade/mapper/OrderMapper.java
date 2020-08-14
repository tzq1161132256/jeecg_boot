package org.jeecg.modules.business.trade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.business.trade.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
public interface OrderMapper extends BaseMapper<Order> {
   public List<Map> getLimitOrder(@Param("cm") Map<String, Object> var1);
   public List<Map> getLimitOrder4marketing(@Param("cm") Map<String, Object> var1);
}
