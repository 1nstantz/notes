package cn.itcast.mq.spring;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;  //注入进来的这玩意并不存在于我们的工程里，是spring的jar包提供的

    //FIXME SpringAMQP如下面的发送有个问题：只能发送已经存在的队列。如果队列不存在发送就失效
    //没有创建队列的代码,创建队列的代码见原生的rabbitmq的demo
    //SpringAMQP创建队列的代码如下：
    //@Bean
    //    public Queue testQueue(){
    //        return new Queue("test.queue");
    //    }
    @Test
    public void simpleQueue(){
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName,message);
        System.out.println("发送完成");
    }

    @Test
    public void workQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message-";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);   //方便看执行过程，别发的太快了
            System.out.println("已发送："+i+"条到队列中");
        }
    }

    //测试FanoutQueue的发送队列：
    //以前是直接发送到队列，现在是要发送到交换机，所以代码略有不同
    /*
        可以发现，FanoutQueue增加了一层交换机，可以多个队列对应多个消费者。
        而且比起WorkQueue，FanoutQueue生产者是先发送到交换机; 而WorkQueue是直接发送到队列
    */
    @Test
    public void testSendFanoutExchange(){
        //交换机名称
        String exchangeName = "itcast.fanout";
        //消息
        String message = "hello, every one!";
        //发送消息到交换机（不是之前的发送到队列了）
        //三个入参，分别是交换机名称、 routingKey(先不管，后面会讲到) 、消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
    //测试DirectQueue的发送队列
    //比起FanoutQueue，DirectQueue并不是发给every one，而是发给BindingKey相同的队列
    //从这往上数第四行代码没说的routingKey，在这里就是BindingKey的意思了
    @Test
    public void testSendDirectExchange(){
        //交换机名称
        String exchangeName = "itcast.direct";
        //消息（比起上面的Fanout 这里是只发送给bindingName为blue的消息队列）
        //你也可以写hello， yellow，然后绑定下面的routingKey为yellow，那就是只发送给bindingName为yellow的队列了
        String message = "hello, blue!";
        //发送消息到交换机
        //三个入参，分别是交换机名称、 BindingKey 、消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }


    //测试TopicQueue的发送队列
    //比起DirectQueue，TopicQueue的唯一区别就是在DIrectQueue的基础上支持通配符
    @Test
    public void testSendTopicExchange(){
        //交换机名称
        String exchangeName = "itcast.topic";
        //消息（比起上面的Fanout 这里是只发送给bindingName为blue的消息队列）
        //你也可以写hello， yellow，然后绑定下面的routingKey为yellow，那就是只发送给bindingName为yellow的队列了
        String message = "大新闻！！！！！！！！！！！！！";
        //发送消息到交换机
        //由于通配符的缘故，下面3行代码可以被不同的监听队列监听到。不信的话可以挨个打开注释试试
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message); //listenTopicQueue1和listenTopicQueue2都能收到
        //rabbitTemplate.convertAndSend(exchangeName, "china.daji", message);   //only listenTopicQueue1能收到
        //rabbitTemplate.convertAndSend(exchangeName, "japan.news", message);   //only listenTopicQueue2能收到
    }

    //发送一个对象
    @Test
    public void testSendObjectQueue(){
        Map<String,Object> msg = new HashMap<>();
        msg.put("name","卢本伟nb");
        msg.put("age","24");
        rabbitTemplate.convertAndSend("object.queue", msg);
    }



    @Test
    public void testSendMessage2SimpleQueue() throws InterruptedException {
        // 1.消息体
        String message = "hello, spring amqp!";
        // 2.全局唯一的消息ID，需要封装到CorrelationData中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 3.添加callback
        correlationData.getFuture().addCallback(
                result -> {
                    if(result.isAck()){
                        // 3.1.ack，消息成功
                        log.debug("消息发送成功, ID:{}", correlationData.getId());
                    }else{
                        // 3.2.nack，消息失败
                        log.error("消息发送到交换机失败, ID:{}, 原因{}",correlationData.getId(), result.getReason());
                    }
                },
                ex -> log.error("消息发送异常, ID:{}, 原因{}",correlationData.getId(),ex.getMessage())
        );
        // 4.发送消息
        rabbitTemplate.convertAndSend("amq.topic", "simple.qh", message, correlationData);

        // 休眠一会儿，等待ack回执
        Thread.sleep(2000);
    }



    @Test
    public void testTTLQueue() {
        // 创建消息
        String message = "hello, ttl queue";
        // 消息ID，需要封装到CorrelationData中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 发送消息
        rabbitTemplate.convertAndSend("ttl.direct", "ttl", message, correlationData);
        // 记录日志
        log.debug("发送消息成功");
    }
}
