package cn.itcast.mq.comfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 什么样的消息会成为死信？
 *
 * - 消息被消费者reject或者返回nack
 * - 消息超时未消费
 * - 队列满了
 *
 * 死信交换机的使用场景是什么？
 *
 * - 如果队列绑定了死信交换机，死信会投递到死信交换机；
 * - 可以利用死信交换机收集所有消费者处理失败的消息（死信），交由人工处理，进一步提高消息队列的可靠性。
 *
 *
 * @author qinhao
 * @date 2022/1/26 - 15:03
 */
@Configuration
public class DeadConfig {
    // 声明普通的 simple.queue队列，并且为其指定死信交换机：dl.direct
    @Bean
    public Queue simpleQueue2(){
        return QueueBuilder.durable("simple.queue") // 指定队列名称，并持久化
                .deadLetterExchange("dl.direct") // 指定死信交换机
                .build();
    }
    // 声明死信交换机 dl.direct
    @Bean
    public DirectExchange dlExchange(){
        return new DirectExchange("dl.direct", true, false);
    }
    // 声明存储死信的队列 dl.queue
    @Bean
    public Queue dlQueue(){
        return new Queue("dl.queue", true);
    }
    // 将死信队列 与 死信交换机绑定
    // 将死信队列 与 死信交换机绑定
    @Bean
    public Binding dlBinding(){
        return BindingBuilder.bind(dlQueue()).to(dlExchange()).with("simple");
    }

}
