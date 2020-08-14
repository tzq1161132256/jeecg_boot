package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Userregistration;
import org.jeecg.modules.business.trade.mapper.UserregistrationMapper;
import org.jeecg.modules.business.trade.service.IUserregistrationService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 用户注册信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class UserregistrationServiceImpl extends ServiceImpl<UserregistrationMapper, Userregistration> implements IUserregistrationService {
    @Override
    public void processUserregistration(Userregistration userregistration) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("projectname",userregistration.getProjectname());
        queryWrapper.eq("telphone",userregistration.getTelphone());
        Userregistration userregistration1 = getOne(queryWrapper,true);
        if(userregistration1 == null){
            userregistration.setId(UUID.randomUUID().toString());
            userregistration.setUpdateTime(new Date());
            userregistration.setCreateTime(new Date());
            save(userregistration);
        }else {
            userregistration.setId(userregistration1.getId());
            userregistration.setUpdateTime(new Date());
            updateById(userregistration);
        }
    }
}
