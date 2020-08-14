package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Instalment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 金融分期
 * @Author: jeecg-boot
 * @Date:   2019-08-21
 * @Version: V1.0
 */
public interface IInstalmentService extends IService<Instalment> {
 public void processInstalment(Instalment instalment) throws Exception;
}
