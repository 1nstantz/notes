package cn.itcast.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author qinhao
 * @date 2022/1/26 - 15:11
 */
@Component
@Slf4j
public class Deadlistenner {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dl.ttl.queue", durable = "true"),
            exchange = @Exchange(name = "dl.ttl.direct"),
            key = "ttl"
    ))
    public void listenDlQueue(String msg){
        log.info("接收到 dl.ttl.queue的延迟消息：{}", msg);
    }
}
