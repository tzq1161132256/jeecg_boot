package org.jeecg.modules.business.mdm.service;

import org.jeecg.modules.business.mdm.entity.Equipmentapply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ipad领用信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IEquipmentapplyService extends IService<Equipmentapply> {
 public void processEquipmentapply(Equipmentapply equipmentapply) throws Exception;
}
