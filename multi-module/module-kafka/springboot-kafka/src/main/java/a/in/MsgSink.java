package a.in;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MsgSink {

	@KafkaListener(topics ={"wsh-topic-01"})
	public void messageSink(String cr) {
		System.out.println("Received: " + cr.toString());
	}
}
