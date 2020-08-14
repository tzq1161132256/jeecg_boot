package org.jeecg.modules.business.mdm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.mdm.entity.Drugstore;
import org.jeecg.modules.business.mdm.mapper.DrugstoreMapper;
import org.jeecg.modules.business.mdm.service.IDrugstoreService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 药店主数据
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class DrugstoreServiceImpl extends ServiceImpl<DrugstoreMapper, Drugstore> implements IDrugstoreService {

}
