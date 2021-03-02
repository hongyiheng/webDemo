//package com.hyh.dang.rabbitmq.receiver;
//
//import com.hyh.dang.rabbitmq.RabbitConfig;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import javax.annotation.Resource;
//
//@Component
//public class OrderReceiver {
//    @Resource
//    private RabbitConfig rabbitConfig;
//
//    @Bean
//    public void checkMessage(){
//        SimpleMessageListenerContainer container =  rabbitConfig.getMessageContainer();
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                /**通过basic.qos方法设置prefetch_count=1，这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message，
//                 换句话说,在接收到该Consumer的ack前,它不会将新的Message分发给它 */
//                channel.basicQos(1);
//                byte[] body = message.getBody();
//
//                System.out.println("接受到的数据为：{}" + new String(body));
//                /**为了保证永远不会丢失消息，RabbitMQ支持消息应答机制。
//                 当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。*/
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//            }
//        });
//    }
//
//
//}
