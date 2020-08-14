package org.jeecg.modules.business.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.business.mq.config.RabbitConfig;
import org.jeecg.modules.business.trade.entity.Bill;
import org.jeecg.modules.business.trade.entity.InsuranceClaims;
import org.jeecg.modules.business.trade.service.IBillService;
import org.jeecg.modules.business.trade.service.IInsuranceClaimsService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by hang.zhou on 2019/7/26.
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_INSURANCE_CLAIMS)
@Slf4j
public class InsuranceClaimsConsumer {
  //  private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
  IInsuranceClaimsService insuranceClaimsService;

    @RabbitHandler
    public void process(String sendMsg, Channel channel, Message message) throws Exception {

        log.info("AckReceiver  : 收到发送消息 " + sendMsg + ",收到消息时间"
                + LocalDateTime.now(ZoneId.systemDefault()));

        try {
            Assert.notNull(sendMsg, "sendMessage 消息体不能为NULL");
            log.info("处理MQ消息");
            // prefetchCount限制每个消费者在收到下一个确认回执前一次可以最大接受多少条消息,通过basic.qos方法设置prefetch_count=1,这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message
            channel.basicQos(1);
            log.info("DirectConsumer {} directMessage :" + message);

            InsuranceClaims model = JSON.parseObject(sendMsg, InsuranceClaims.class);
            model.setUpdateTime(new Date());
            model.setCreateTime(new Date());
            insuranceClaimsService.saveOrUpdate(model);
            log.info("model={}",model);
            // 确认消息已经消费成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("MQ消息处理异常，消息ID：{}，消息体:{}", message.getMessageProperties().getCorrelationId(),sendMsg,e);
            // 拒绝当前消息，并把消息返回原队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }


    }
}
