package org.jeecg.modules.business.mdm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.mdm.entity.Equipmentapply;
import org.jeecg.modules.business.mdm.mapper.EquipmentapplyMapper;
import org.jeecg.modules.business.mdm.service.IEquipmentapplyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: ipad领用信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class EquipmentapplyServiceImpl extends ServiceImpl<EquipmentapplyMapper, Equipmentapply> implements IEquipmentapplyService {
    @Override
    public void processEquipmentapply(Equipmentapply equipmentapply) throws Exception {
        equipmentapply.setUpdateTime(new Date());
        equipmentapply.setCreateTime(new Date());
        saveOrUpdate(equipmentapply);
    }
}
