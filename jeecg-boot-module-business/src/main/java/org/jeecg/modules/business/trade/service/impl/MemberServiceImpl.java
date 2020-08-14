package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.business.trade.entity.Member;
import org.jeecg.modules.business.trade.mapper.MemberMapper;
import org.jeecg.modules.business.trade.service.IMemberService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.UUID;

/**
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Override
    public void processMember(Member member) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("orderno",member.getOrderno());
        Member member1 =  getOne(queryWrapper,true);
        if(member1 == null) {
            member.setId(UUID.randomUUID().toString());
            member.setUpdateTime(new Date());
            member.setCreateTime(new Date());
            save(member);
        }else {
            member.setId(member1.getId());
            member.setUpdateTime(new Date());
            updateById(member);
        }
    }
}
