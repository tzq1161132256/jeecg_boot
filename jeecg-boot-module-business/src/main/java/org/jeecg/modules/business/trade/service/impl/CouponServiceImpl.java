package org.jeecg.modules.business.trade.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.business.trade.entity.Coupon;
import org.jeecg.modules.business.trade.mapper.CouponMapper;
import org.jeecg.modules.business.trade.service.ICouponService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 优惠券信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Service
@DS("multi-datasource1")
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

}
