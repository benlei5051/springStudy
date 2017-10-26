package org.andy.producer2;

import org.andy.producer2.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages ={"org.andy.common","org.andy.producer2"} )
@RestController
public class SourceApplication {

	@Autowired
	private SendService service;

	@RequestMapping(value = "/send/{msg}", method = RequestMethod.GET)
	public void send(@PathVariable("msg") String msg){
		service.sendMessage(msg);
	}

	public static void main(String[] args) {

//		String[] args2 = new String[]{"--server.port=8800","--spring.profiles.active=aout"};
		SpringApplication.run(SourceApplication.class, args);

	}

}
