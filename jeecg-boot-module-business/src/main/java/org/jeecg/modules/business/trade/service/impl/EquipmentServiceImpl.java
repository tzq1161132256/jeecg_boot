package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Equipment;
import org.jeecg.modules.business.trade.mapper.EquipmentMapper;
import org.jeecg.modules.business.trade.service.IEquipmentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: ipad登录信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
    @Override
    public void processEquipment(Equipment equipment) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("drugstoreid",equipment.getDrugstoreid());
        queryWrapper.eq("equipmentid",equipment.getEquipmentid());
        queryWrapper.eq("lastusetime",equipment.getLastusetime());

        Equipment equipment1 = getOne(queryWrapper,true);
        if(equipment1 == null) {
            equipment.setUpdateTime(new Date());
            equipment.setCreateTime(new Date());
            equipment.setId(UUID.randomUUID().toString());
            save(equipment);
        }else {
            equipment.setId(equipment1.getId());
            equipment.setUpdateTime(new Date());
            updateById(equipment);
        }
    }
}
