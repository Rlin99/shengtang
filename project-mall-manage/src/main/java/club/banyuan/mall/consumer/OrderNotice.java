package club.banyuan.mall.consumer;

import club.banyuan.mall.service.SendSms;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OrderNotice {

    @Autowired
    private SendSms sendSms;

    @RabbitListener( bindings = @QueueBinding(
            value = @Queue,//创建临时队
            exchange = @Exchange(value = "direct",type = "direct"),
            key = {"info","error","warning"}
    ))
    public void recesive1(String message)
    {
        System.out.println("message1:"+message);
//        HashMap<String, Object> param = new HashMap<>();
//        //code是配置在模板中的参数
//        param.put("code", message);
//        boolean isSend = sendSms.send("18082326379", param);
//        if (isSend) {
//            System.out.println("发送成功！");
//        } else {
//            System.out.println("发送失败！");
//        }

    }

    @RabbitListener( bindings = @QueueBinding(
            value = @Queue,//创建临时队列
            exchange = @Exchange(value = "direct",type = "direct"),
            key = {"warning"}
    ))
    public void recesive2(String message){
        System.out.println("message2:"+message);
    }
}
