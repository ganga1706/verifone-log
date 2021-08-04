package com.logparsing.logparsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.logparsing.logparsing.service.Read;
import com.logparsing.logparsing.service.Write;


@Component
public class Startup implements ApplicationRunner {
	
	@Autowired
	Read read;
	
	@Autowired
	Write write;
	
	@Autowired
    private ConfigurableApplicationContext context;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("########################### Start #########################");
		read.dimeboxValidationTimeTakenFileWrite();
		write.csvwriter();
		System.out.println("########################### end #########################");
		System.exit(SpringApplication.exit(context));
	}

}
