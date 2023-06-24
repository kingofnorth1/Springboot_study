package com.dqk.Service;

import com.dqk.domain.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
        /**
     * Publish/Subscribe工作模式接收，处理邮件业务
     * @param message
     */
    @RabbitListener(queues = "fanout_queue_email")
    public void psubConsumerEmail(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("邮件业务接收到消息： "+s);

    }
    /**
     * Publish/Subscribe工作模式接收，处理短信业务
     * @param message
     */
    @RabbitListener(queues = "fanout_queue_sms")
    public void psubConsumerSms(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("短信业务接收到消息： "+s);
    }

    /**
     * 注解方式进行publish/Subscribe
     * @param user
     */
    @RabbitListener(bindings = @QueueBinding(value=@Queue("fanout_queue_sms"),
            exchange=@Exchange(value="fanout_exchange",type="fanout")))
    public void psubConsumerSmsAno(User user){
        System.out.println("短信业务接受到消息:"+user);
    }
    @RabbitListener(bindings = @QueueBinding(value=@Queue("fanout_queue_email"),
            exchange=@Exchange(value="fanout_exchange",type="fanout")))
    public void psubConsumerEmailAno(User user){
        System.out.println("邮件业务接受到消息:"+user);
    }

    /**
     * 路由模式消息接收
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value=@Queue("fanout_queue_error"),
            exchange=@Exchange(value="routing_exchange",type="direct"),key="error_routing_key"))
    public void psubConsumerError(String message){
        System.out.println("接收到error级别日志消息:"+message);
    }
    @RabbitListener(bindings = @QueueBinding(value=@Queue("fanout_queue_all"),
            exchange=@Exchange(value="routing_exchange",type="direct"),key={"error_routing_key","info_routing_key","warning_routing_key"}))
    public void psubConsumerAll(String message){
        System.out.println("接收到info、error、warning级别日志消息:"+message);
    }
    /**
     *  3.1、通配符模式消息接收，进行邮件业务订阅处理
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("topic_queue_email"),exchange =@Exchange(value = "topic_exchange",type = "topic"),key = "info.#.email.#"))
    public void topicConsumerEmail(String message) {
        System.out.println("接收到邮件订阅需求处理消息： "+message);
    }
    /**
     *  3.2、通配符模式消息接收，进行短信业务订阅处理
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("topic_queue_sms"),exchange =@Exchange(value = "topic_exchange",type = "topic"),key = "info.#.sms.#"))
    public void topicConsumerSms(String message) {
        System.out.println("接收到短信订阅需求处理消息： "+message);
    }
}
