package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Usercumulate;
import org.jeecg.modules.business.trade.mapper.UsercumulateMapper;
import org.jeecg.modules.business.trade.service.IUsercumulateService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 微信公众号关注人数
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class UsercumulateServiceImpl extends ServiceImpl<UsercumulateMapper, Usercumulate> implements IUsercumulateService {
    @Override
    public void processUsercumulate(Usercumulate usercumulate) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("refdate",usercumulate.getRefdate());
        queryWrapper.eq("gzhname",usercumulate.getGzhname());
        Usercumulate usercumulate1 = getOne(queryWrapper,true);
        if (usercumulate1 == null) {
            usercumulate.setId(UUID.randomUUID().toString());
            usercumulate.setUpdateTime(new Date());
            usercumulate.setCreateTime(new Date());
            save(usercumulate);
        }else {
            usercumulate.setId(usercumulate1.getId());
            usercumulate.setUpdateTime(new Date());
            updateById(usercumulate);
        }
    }
}
