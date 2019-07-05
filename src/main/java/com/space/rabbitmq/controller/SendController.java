package com.space.rabbitmq.controller;

import com.space.rabbitmq.sender.FirstSender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.lang.Thread.*;

/**
 * @author zhuzhe
 * @date 2018/5/25 16:00
 * @email 1529949535@qq.com
 */
@RestController
public class SendController {

    @Autowired
    private FirstSender firstSender;

    /**
     * 发送消息
     * @param message
     * @return
     */
    @GetMapping("/sendfirst")
    public String send(String message){
        String uuid = UUID.randomUUID().toString();
        firstSender.send(uuid,"ni hao");
        return uuid;
    }

    /**
     * 消费者firstConsumer 可以是多个，一对多关系的队列 ，注意吧监听者配置多个
     * @param message
     * @return
     */
      @GetMapping("/sendfirst2")
    public String send222(String message){

          for (int i = 0; i < 10; i++) {
              String uuid = UUID.randomUUID().toString();
              firstSender.send(uuid, "ni hao"+i);
          }
        return null;
    }

    /**
     * topic 模式，通配符模式：把消息交给符合rou'ting key（路由模式）的队列
     */
    @Autowired
    private AmqpTemplate amqpTemplate;
    @GetMapping("/topic")
    public String sendTopicsss(String message){
        amqpTemplate.convertAndSend("spring.topic.exchange", "user.insert", "新增用户");
        amqpTemplate.convertAndSend("spring.topic.exchange", "user.delete", "删除用户");
        amqpTemplate.convertAndSend("spring.topic.exchange", "student.insert", "新增学生");
        amqpTemplate.convertAndSend("spring.topic.exchange", "student.delete", "删除学生");
        return null;

    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 尝试 RabbitTemplate 和AmqpTemplate 是否有区别
     * @param message
     * @return
     */
    @GetMapping("/topic2")
    public String sendTopi2(String message){
        rabbitTemplate.convertAndSend("spring.topic.exchange", "user.insert", "新增用户");
        rabbitTemplate.convertAndSend("spring.topic.exchange", "user.delete", "删除用户");
        rabbitTemplate.convertAndSend("spring.topic.exchange", "student.insert", "新增学生");
        rabbitTemplate.convertAndSend("spring.topic.exchange", "student.delete", "删除学生");
        return null;

    }
    @GetMapping("/fan")
    public String fon(String message){
       rabbitTemplate.convertAndSend("fanoutExchange","","hello,nihao");
        return null;

    }

    /**
     *   fanout 模式 ，广播模式：将消息交给所有绑定到交换机的队列，每个消费者都会收到同一条消息
     * @throws InterruptedException
     */
    @GetMapping("/fanout")
    public void fanout() throws InterruptedException {
        String msg = "RabbitMQ fanout ...";
    for (int i = 0; i < 10; i++) {
        // 这里注意细节，第二个参数需要写，不然第一个参数就变成routingKey了 a
         rabbitTemplate.convertAndSend("spring.fanout.exchange", "", msg + i); }
    Thread.sleep(5000);
}

    /**
     * direct模式，定向模式：把消息交给符合指定 rotingKey 的队列
     * @throws InterruptedException
     */
    @GetMapping("/direct")
    public void direct() throws InterruptedException {
        String msg = "RabbitMQ direct ..."; for (int i = 0; i < 10; i++)
        { amqpTemplate.convertAndSend("spring.direct.exchange", "direct", msg + i); }
        Thread.sleep(5000); }


}
