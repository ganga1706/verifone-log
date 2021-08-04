package com.logparsing.logparsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.logparsing.logparsing.service.Read;
import com.logparsing.logparsing.service.Write;

@SpringBootApplication
public class LogparsingApplication {
//	implements CommandLineRunner

//	@Autowired
//	Read read;
//	
//	@Autowired
//	Write write;
//	
//	@Autowired
//    private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(LogparsingApplication.class, args);
	}

	@Bean
	public TodoWebProperties propertyConfigurers() {
		TodoWebProperties ppc = new TodoWebProperties();
		Resource[] resources = new ClassPathResource[] { new ClassPathResource("message/message.properties") };
		ppc.setLocations(resources);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("########################### Start #########################");
//		read.dimeboxValidationTimeTakenFileWrite();
//		write.csvwriter();
//		System.out.println("########################### end #########################");
//		System.exit(SpringApplication.exit(context));
//	}
}
