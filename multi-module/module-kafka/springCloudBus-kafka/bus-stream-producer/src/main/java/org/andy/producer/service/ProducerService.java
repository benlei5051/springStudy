package org.andy.producer.service;

import org.andy.kafka.bean.PushMessage;
import org.andy.kafka.event.PushMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: andy
 * @Date: 2017/9/25 15:25
 * @Description:
 */
@Service
public class ProducerService {
    @Autowired
    private ApplicationContext context;

//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public void testEvent() {
       PushMessage pushMessage=new PushMessage();
       pushMessage.setContent("测试内容");
       pushMessage.setTitle("测试标题");
       // 第三个参数用来限定哪些服务需要接收处理这个事件，其他的将不会处理这个事件。
       PushMessageEvent event =
               new PushMessageEvent(this, context.getId(), null, pushMessage);
       context.publishEvent(event);
    }
}
