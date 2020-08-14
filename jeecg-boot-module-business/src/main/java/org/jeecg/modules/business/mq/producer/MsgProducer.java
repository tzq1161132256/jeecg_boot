package org.jeecg.modules.business.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.business.mq.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by hang.zhou on 2019/7/26.
 */
@Service
@Slf4j
public class MsgProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback  {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
/*    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }*/
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_DEFAULT, RabbitConfig.ROUTINGKEY_A, content, correlationId);
    }

    /**
     * convertAndSend 异步,消息是否发送成功用ConfirmCallback和ReturnCallback回调函数类确认。
     * 发送MQ消息
     */
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }
    /**
     * sendMessageAndReturn 当发送消息过后,该方法会一直阻塞在哪里等待返回结果,直到请求超时,配置spring.rabbitmq.template.reply-timeout来配置超时时间。
     * 发送MQ消息并返回结果
     */
    public Object sendMessageAndReturn(String exchangeName, String routingKey, Object message) {
        return rabbitTemplate.convertSendAndReceive(exchangeName, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }


    /**
     * 实现消息发送到RabbitMQ交换器后接收ack回调,如果消息发送确认失败就进行重试.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(" 回调id:" + correlationData);
        if (ack) {
            log.info("消息发送成功,消息ID:{}", correlationData.getId());
        } else {
            log.info("消息发送失败，消息ID:{}", correlationData.getId());
        }
    }

    /**
     * 实现消息发送到RabbitMQ交换器,但无相应队列与交换器绑定时的回调.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息发送失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}",replyCode, replyText, exchange, routingKey, new String(message.getBody()));
    }

}

