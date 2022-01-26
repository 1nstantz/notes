package cn.itcast.mq.comfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qinhao
 * @date 2022/1/26 - 14:31
 */
@Configuration
public class CommonConfig {

    @Bean
    public DirectExchange simpleDirect() {
        //第一个是交换机的持久化，第二个是交换机绑定的队列都删除后，交换机是否自动删除
        return new DirectExchange("simple.direct", true, false);
    }


    @Bean
    public Queue simplequeue() {
        //队列持久化的创建方式
        return QueueBuilder.durable("simple.queue").build();
    }


}
