package org.jeecg.modules.business.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hang.zhou on 2019/7/26.
 */
@Configuration
@Slf4j
public class RabbitConfig {

 //   private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_DEFAULT = "bi_direct_exchange";

    public static final String QUEUE_A = "QUEUE_A";
    // 众惠保险
    public static final String QUEUE_INSURANCE_ZHONGHUI = "QUEUE_INSURANCE_ZHONGHUI";

    //招商仁和保险
    public static final String QUEUE_INSURANCE_ZHAOSHANGRENHE = "QUEUE_INSURANCE_ZHAOSHANGRENHE";

    //订单
    public static final String QUEUE_ORDERALL = "QUEUE_ORDERALL";

    // 处方信息
    public static final String QUEUE_PRESCRIPTION = "QUEUE_PRESCRIPTION";

    // 慢病核销账单
    public static final String QUEUE_WRITEOFF = "QUEUE_WRITEOFF";

    // 药康付会员信息
    public static final String QUEUE_MEMBER = "QUEUE_MEMBER";

    // 慢病无忧对账单
    public static final String QUEUE_BILL = "QUEUE_BILL";

    // 保险每期明细
    public static final String QUEUE_INSURANCE_DETAIL = "QUEUE_INSURANCE_DETAIL";

    // 华夏保险
    public static final String QUEUE_INSURANCE_HUAXIA = "QUEUE_INSURANCE_HUAXIA";

    // 复星保险
    public static final String QUEUE_INSURANCE_FUXING = "QUEUE_INSURANCE_FUXING";

    // 药房主数据
    public static final String QUEUE_DRUGSTORE = "QUEUE_DRUGSTORE";

    // pos交易信息
    public static final String QUEUE_POS_TRANSACTION = "QUEUE_POS_TRANSACTION";

    // pos机领用信息
    public static final String QUEUE_POS_APPLY = "QUEUE_POS_APPLY";

    // 保险报销信息
    public static final String QUEUE_INSURANCE_CLAIMS = "QUEUE_INSURANCE_CLAIMS";

    // 早鸟券信息
    public static final String QUEUE_TICKET = "QUEUE_TICKET";

    // ipad领用信息
    public static final String QUEUE_EQUIPMENTAPPLY = "QUEUE_EQUIPMENTAPPLY";

    // ipad登录信息
    public static final String QUEUE_EQUIPMENT = "QUEUE_EQUIPMENT";

    // ipad登录信息
    public static final String QUEUE_COUPON = "QUEUE_COUPON";

    // 微信公众号关注人数
    public static final String QUEUE_USERCUMULATE = "QUEUE_USERCUMULATE";

    // 用户注册信息
    public static final String QUEUE_USERREGISTRATION = "QUEUE_USERREGISTRATION";

    // 项目药店信息
    public static final String QUEUE_DRUGSTORE4PROJECT = "QUEUE_DRUGSTORE4PROJECT";

    // 项目入组信息
    public static final String QUEUE_ENTERINGGROUP = "QUEUE_ENTERINGGROUP";

    // 金融分期项目还款信息
    public static final String QUEUE_REPAYMENTS = "QUEUE_REPAYMENTS";

    // 疗效保险
    public static final String QUEUE_INSURANCE = "QUEUE_INSURANCE";

    // 金融分期
    public static final String QUEUE_INSTALMENT = "QUEUE_INSTALMENT";

    // 华夏保险（升级版）
    public static final String QUEUE_INSURANCE_HUAXIA2 = "QUEUE_INSURANCE_HUAXIA2";

    /****************************************************************************************************************************************************/
    /****************************************************************************************************************************************************/

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_INSURANCE_ZHONGHUI = "queue_insurance_zhonghui";
    public static final String ROUTINGKEY_INSURANCE_ZHAOSHANGRENHE = "queue_insurance_zhaoshangrenhe";
    public static final String ROUTINGKEY_ORDERALL = "queue_order";
    public static final String ROUTINGKEY_PRESCRIPTION = "queue_prescription";
    public static final String ROUTINGKEY_WRITEOFF = "queue_writeoff";
    public static final String ROUTINGKEY_MEMBER = "queue_member";
    public static final String ROUTINGKEY_BILL = "queue_bill";
    public static final String ROUTINGKEY_INSURANCE_DETAIL = "queue_insurance_detail";
    public static final String ROUTINGKEY_INSURANCE_HUAXIA = "queue_insurance_huaxia";
    public static final String ROUTINGKEY_INSURANCE_FUXING = "queue_insurance_fuxing";
    public static final String ROUTINGKEY_DRUGSTORE = "queue_drugstore";
    public static final String ROUTINGKEY_POS_TRANSACTION = "queue_pos_transaction";
    public static final String ROUTINGKEY_POS_APPLY = "queue_pos_apply";
    public static final String ROUTINGKEY_INSURANCE_CLAIMS = "queue_insurance_claims";
    public static final String ROUTINGKEY_TICKET = "queue_ticket";
    public static final String ROUTINGKEY_EQUIPMENTAPPLY = "queue_equipment_apply";
    public static final String ROUTINGKEY_EQUIPMENT = "queue_equipment";
    public static final String ROUTINGKEY_COUPON = "queue_coupon";
    public static final String ROUTINGKEY_USERCUMULATE = "queue_subscription";
    public static final String ROUTINGKEY_USERREGISTRATION = "queue_userregistration";
    public static final String ROUTINGKEY_DRUGSTORE4PROJECT = "queue_drugstore4project";
    public static final String ROUTINGKEY_ENTERINGGROUP = "queue_enteringgroup";
    public static final String ROUTINGKEY_REPAYMENTS = "queue_repayments";
    public static final String ROUTINGKEY_INSURANCE = "queue_insurance";
    public static final String ROUTINGKEY_INSTALMENT = "queue_instalment";
    public static final String ROUTINGKEY_INSURANCE_HUAXIA2 = "queue_insurance_huaxia2";

   /* @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/operation_bi");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }*/


    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_DEFAULT);
    }

    /****************************************************************************************************************************************************/
    /****************************************************************************************************************************************************/

    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceZhonghui() {
        return new Queue(QUEUE_INSURANCE_ZHONGHUI, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceZhaoshangrenhe() {
        return new Queue(QUEUE_INSURANCE_ZHAOSHANGRENHE, true); //队列持久
    }

    @Bean
    public Queue queueOrderAll() {
        return new Queue(QUEUE_ORDERALL, true); //队列持久
    }

    @Bean
    public Queue queuePrescription() {
        return new Queue(QUEUE_PRESCRIPTION, true); //队列持久
    }

    @Bean
    public Queue queueWriteoff() {
        return new Queue(QUEUE_WRITEOFF, true); //队列持久
    }

    @Bean
    public Queue queueMember() {
        return new Queue(QUEUE_MEMBER, true); //队列持久
    }

    @Bean
    public Queue queueBill() {
        return new Queue(QUEUE_BILL, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceDetail() {
        return new Queue(QUEUE_INSURANCE_DETAIL, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceHuaxia() {
        return new Queue(QUEUE_INSURANCE_HUAXIA, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceFuxing() {
        return new Queue(QUEUE_INSURANCE_FUXING, true); //队列持久
    }

    @Bean
    public Queue queueDrugstore() {
        return new Queue(QUEUE_DRUGSTORE, true); //队列持久
    }

    @Bean
    public Queue queuePosTransaction() {
        return new Queue(QUEUE_POS_TRANSACTION, true); //队列持久
    }

    @Bean
    public Queue queuePosApply() {
        return new Queue(QUEUE_POS_APPLY, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceClaims() {
        return new Queue(QUEUE_INSURANCE_CLAIMS, true); //队列持久
    }

    @Bean
    public Queue queueTicket() {
        return new Queue(QUEUE_TICKET, true); //队列持久
    }

    @Bean
    public Queue queueEquipmentApply() {
        return new Queue(QUEUE_EQUIPMENTAPPLY, true); //队列持久
    }

    @Bean
    public Queue queueEquipment() {
        return new Queue(QUEUE_EQUIPMENT, true); //队列持久
    }

    @Bean
    public Queue queueCoupon() {
        return new Queue(QUEUE_COUPON, true); //队列持久
    }

    @Bean
    public Queue queueUsercumulate() {
        return new Queue(QUEUE_USERCUMULATE, true); //队列持久
    }

    @Bean
    public Queue queueUserregistration() {
        return new Queue(QUEUE_USERREGISTRATION, true); //队列持久
    }

    @Bean
    public Queue queueDrugstore4project() {
        return new Queue(QUEUE_DRUGSTORE4PROJECT, true); //队列持久
    }

    @Bean
    public Queue queueEnteringgroup() {
        return new Queue(QUEUE_ENTERINGGROUP, true); //队列持久
    }

    @Bean
    public Queue queueRepayments() {
        return new Queue(QUEUE_REPAYMENTS, true); //队列持久
    }

    @Bean
    public Queue queueInsurance() {
        return new Queue(QUEUE_INSURANCE, true); //队列持久
    }

    @Bean
    public Queue queueInstalment() {
        return new Queue(QUEUE_INSTALMENT, true); //队列持久
    }

    @Bean
    public Queue queueInsuranceHuaxia2() {
        return new Queue(QUEUE_INSURANCE_HUAXIA2, true); //队列持久
    }


    /****************************************************************************************************************************************************/
    /****************************************************************************************************************************************************/
    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingInsuranceZhonghui(){
        return BindingBuilder.bind(queueInsuranceZhonghui()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_ZHONGHUI);
    }

    @Bean
    public Binding bindingZhaoshangrenhe(){
        return BindingBuilder.bind(queueInsuranceZhaoshangrenhe()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_ZHAOSHANGRENHE);
    }

    @Bean
    public Binding bindingOrderAll(){
        return BindingBuilder.bind(queueOrderAll()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_ORDERALL);
    }

    @Bean
    public Binding bindingPrescription(){
        return BindingBuilder.bind(queuePrescription()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_PRESCRIPTION);
    }

    @Bean
    public Binding bindingWriteoff(){
        return BindingBuilder.bind(queueWriteoff()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_WRITEOFF);
    }

    @Bean
    public Binding bindingMember(){
        return BindingBuilder.bind(queueMember()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_MEMBER);
    }

    @Bean
    public Binding bindingBill(){
        return BindingBuilder.bind(queueBill()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_BILL);
    }

    @Bean
    public Binding bindingInsuranceDetail(){
        return BindingBuilder.bind(queueInsuranceDetail()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_DETAIL);
    }

    @Bean
    public Binding bindingInsuranceHuaxia(){
        return BindingBuilder.bind(queueInsuranceHuaxia()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_HUAXIA);
    }

    @Bean
    public Binding bindingInsuranceFuxing(){
        return BindingBuilder.bind(queueInsuranceFuxing()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_FUXING);
    }

    @Bean
    public Binding bindingDrugstore(){
        return BindingBuilder.bind(queueDrugstore()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_DRUGSTORE);
    }

    @Bean
    public Binding bindingPosTransaction(){
        return BindingBuilder.bind(queuePosTransaction()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_POS_TRANSACTION);
    }

    @Bean
    public Binding bindingPosApply(){
        return BindingBuilder.bind(queuePosApply()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_POS_APPLY);
    }

    @Bean
    public Binding bindingInsuranceClaims(){
        return BindingBuilder.bind(queueInsuranceClaims()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_CLAIMS);
    }

    @Bean
    public Binding bindingTicket(){
        return BindingBuilder.bind(queueTicket()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_TICKET);
    }

    @Bean
    public Binding bindingEquipmentApply(){
        return BindingBuilder.bind(queueEquipmentApply()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_EQUIPMENTAPPLY);
    }

    @Bean
    public Binding bindingEquipment(){
        return BindingBuilder.bind(queueEquipment()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_EQUIPMENT);
    }

    @Bean
    public Binding bindingCoupon(){
        return BindingBuilder.bind(queueCoupon()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_COUPON);
    }

    @Bean
    public Binding bindingUsercumulate(){
        return BindingBuilder.bind(queueUsercumulate()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_USERCUMULATE);
    }

    @Bean
    public Binding bindingUserregistration(){
        return BindingBuilder.bind(queueUserregistration()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_USERREGISTRATION);
    }

    @Bean
    public Binding bindingDrugstore4project(){
        return BindingBuilder.bind(queueDrugstore4project()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_DRUGSTORE4PROJECT);
    }

    @Bean
    public Binding bindingEnteringgroup(){
        return BindingBuilder.bind(queueEnteringgroup()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_ENTERINGGROUP);
    }

    @Bean
    public Binding bindingRepayments(){
        return BindingBuilder.bind(queueRepayments()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_REPAYMENTS);
    }

    @Bean
    public Binding bindingInsurance(){
        return BindingBuilder.bind(queueInsurance()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE);
    }

    @Bean
    public Binding bindingInstalment(){
        return BindingBuilder.bind(queueInstalment()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSTALMENT);
    }

    @Bean
    public Binding bindingInsuranceHuaxia2(){
        return BindingBuilder.bind(queueInsuranceHuaxia2()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_INSURANCE_HUAXIA2);
    }
}
