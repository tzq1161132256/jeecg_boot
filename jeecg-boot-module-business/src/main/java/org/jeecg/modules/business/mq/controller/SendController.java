package org.jeecg.modules.business.mq.controller;

import com.alibaba.fastjson.JSON;
import org.jeecg.modules.business.insurance.entity.InsuranceZhaoshangrenhe;
import org.jeecg.modules.business.insurance.entity.InsuranceZhonghui;
import org.jeecg.modules.business.mq.config.RabbitConfig;
import org.jeecg.modules.business.mq.producer.MsgProducer;
import org.jeecg.modules.business.trade.entity.Order;
import org.jeecg.modules.business.trade.entity.Orderdetail;
import org.jeecg.modules.business.trade.entity.Prescription;
import org.jeecg.modules.business.trade.vo.OrderPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hang.zhou on 2019/7/29.
 */
@RestController
@RequestMapping("/mq")
public class SendController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);
    @Autowired
    private MsgProducer msgProducer;

    /**
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String ackSend() {

        Prescription prescription = new Prescription();
        prescription.setProjectname("默克怡可安（怡可安心）");
        prescription.setOrdercode("100000K10020190628165936");
        prescription.setHospitalname("医院测试");
        prescription.setDoctorname("医生测试");

        String msg = JSON.toJSONString(prescription);
        msgProducer.sendMessage(RabbitConfig.EXCHANGE_DEFAULT, RabbitConfig.ROUTINGKEY_PRESCRIPTION, msg);

        return "ok";
    }
}
