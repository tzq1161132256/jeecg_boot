package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Postransaction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: pos机交易信息
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IPostransactionService extends IService<Postransaction> {
 public void processPostransaction(Postransaction postransaction) throws Exception;
}
