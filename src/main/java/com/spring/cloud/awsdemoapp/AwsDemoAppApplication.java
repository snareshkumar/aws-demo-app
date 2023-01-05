package com.spring.cloud.awsdemoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.sqs.AmazonSQSAsync;

@SpringBootApplication
public class AwsDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsDemoAppApplication.class, args);
	}

}
