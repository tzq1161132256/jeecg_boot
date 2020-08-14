package org.jeecg.modules.business.mdm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.mdm.entity.Posapply;
import org.jeecg.modules.business.mdm.mapper.PosapplyMapper;
import org.jeecg.modules.business.mdm.service.IPosapplyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: pos机领用记录
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class PosapplyServiceImpl extends ServiceImpl<PosapplyMapper, Posapply> implements IPosapplyService {
    @Override
    public void processPosapply(Posapply posapply) throws Exception {
        posapply.setUpdateTime(new Date());
        posapply.setCreateTime(new Date());
        saveOrUpdate(posapply);
    }
}
