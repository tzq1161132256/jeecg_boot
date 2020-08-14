package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Writeoff;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 慢病核销对账单
 * @Author: jeecg-boot
 * @Date:   2019-08-12
 * @Version: V1.0
 */
public interface IWriteoffService extends IService<Writeoff> {
  /*处理慢病核销账单*/
    public void processWriteoff(Writeoff writeoff) throws Exception;
}
