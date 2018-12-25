package a.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.support.MessageBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableBinding(Source.class)
public class SendService {

	@Autowired
	private Source source;

	public void sendMessage(String msg) {

		try {

			source.output().send(MessageBuilder.withPayload(msg).build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
