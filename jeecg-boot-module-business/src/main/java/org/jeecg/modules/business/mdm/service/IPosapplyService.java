package org.jeecg.modules.business.mdm.service;

import org.jeecg.modules.business.mdm.entity.Posapply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: pos机领用记录
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IPosapplyService extends IService<Posapply> {
  public  void processPosapply(Posapply posapply) throws Exception;
}
