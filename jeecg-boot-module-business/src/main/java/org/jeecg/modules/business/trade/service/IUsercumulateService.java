package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Usercumulate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 微信公众号关注人数
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
public interface IUsercumulateService extends IService<Usercumulate> {
public void processUsercumulate(Usercumulate usercumulate) throws Exception;
}
