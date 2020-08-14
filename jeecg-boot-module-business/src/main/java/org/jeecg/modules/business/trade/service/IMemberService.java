package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 会员信息
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
public interface IMemberService extends IService<Member> {
   public  void processMember(Member member) throws Exception;
}
