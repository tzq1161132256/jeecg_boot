package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Enteringgroup;
import org.jeecg.modules.business.trade.mapper.EnteringgroupMapper;
import org.jeecg.modules.business.trade.service.IEnteringgroupService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 项目入组信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class EnteringgroupServiceImpl extends ServiceImpl<EnteringgroupMapper, Enteringgroup> implements IEnteringgroupService {

}
