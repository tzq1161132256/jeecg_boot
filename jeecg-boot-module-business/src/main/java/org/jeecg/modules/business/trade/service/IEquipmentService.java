package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ipad登录信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IEquipmentService extends IService<Equipment> {
public void processEquipment(Equipment equipment) throws Exception;

}
