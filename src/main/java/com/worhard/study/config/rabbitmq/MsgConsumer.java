package com.worhard.study.config.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author luowen
 * @description
 * @since 2020/12/23
 */
@Component
public class MsgConsumer {

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanoutMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("received Fanout message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME1, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanout1Msg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("received Fanout1 message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitConfig.DIRECT_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_DIRECT_EXCHANGE),
                                    key = RabbitConfig.DIRECT_ROUTINGKEY)
                    })
    @RabbitHandler
    public void processDirectMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("received Direct message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(value = @Queue(value = RabbitConfig.TOPIC_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_TOPIC_EXCHANGE, type = "topic"),
                                    key = RabbitConfig.TOPIC_ROUTINGKEY)
                    })
    @RabbitHandler
    public void processTopicMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        System.out.println("received Topic message : " + msg);
    }

}
