package com.worhard.study.config.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * @author luowen
 * @description
 * @since 2020/12/23
 */
@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;


    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); // rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }


    public void send2FanoutTestQueue(String message){
        // 生成ack id
        CorrelationData ackid = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_FANOUT_EXCHANGE,
                "", message,ackid);
    }

    public void send2DirectTestQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_DIRECT_EXCHANGE,
                RabbitConfig.DIRECT_ROUTINGKEY, massage);
    }

    public void send2TopicTestAQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE,
                "test.aaa", massage);
    }

    public void send2TopicTestBQueue(String massage){
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE,
                "test.bbb", massage);
    }


    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("成功消费");
        } else {
            System.out.println("消费失败:" + cause);
        }
    }

}
