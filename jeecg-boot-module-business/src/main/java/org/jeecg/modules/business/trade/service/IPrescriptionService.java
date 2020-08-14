package org.jeecg.modules.business.trade.service;

import org.jeecg.modules.business.trade.entity.Prescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 处方信息表
 * @Author: jeecg-boot
 * @Date:   2019-08-09
 * @Version: V1.0
 */
public interface IPrescriptionService extends IService<Prescription> {

    /*
    * 处理处方信息
    * */
    public void processPrescription(Prescription prescription) throws Exception;
}
