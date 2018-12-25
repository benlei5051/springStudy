package a.out;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableBinding(Source.class)
public class TimeSource {

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
    public MessageSource<String> timerMessageSource() {
        return () -> {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(simpleDateFormat.format(date));
            return new GenericMessage<>(simpleDateFormat.format(date));
        };


    }

    //	同样用到了上文提到的注解@EnableBinding，因为是要发送消息，所以绑定了发送接口Source。
    //@InboundChannelAdapter(value = Source.OUTPUT)，会一直的向消息队列中发消息
    //	另外，还有@InboundChannelAdapter注解，发送接口。
    @InboundChannelAdapter(value = Source.OUTPUT)
    public String test() {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(format+"##############################");
        return format;
    }
}
