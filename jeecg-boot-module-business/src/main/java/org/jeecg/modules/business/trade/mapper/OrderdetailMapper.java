package org.jeecg.modules.business.trade.mapper;

import java.util.List;
import org.jeecg.modules.business.trade.entity.Orderdetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 订单明细
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
public interface OrderdetailMapper extends BaseMapper<Orderdetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<Orderdetail> selectByMainId(String mainId);
}
