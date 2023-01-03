package com.spring.cloud.awsdemoapp.sqs;

import javax.management.RuntimeErrorException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class SqsDemo {

    org.slf4j.Logger log = LoggerFactory.getLogger(SqsDemo.class);

    @Autowired
    private QueueMessagingTemplate sqsTemplate;

    @Value("${cloud.aws.end_point}")
    private String sqsEndPoint;

    @GetMapping("/{message}")
    public String publishMessage(@PathVariable String message) throws MessagingException {
        sqsTemplate.convertAndSend(sqsEndPoint, message);
        return "published successfully";
    }

    @SqsListener(value = "sqs-queue1", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeMessage(String message) throws Exception {

        if (message.equalsIgnoreCase("naresh")) {
            log.info("Exception occurred {}", message);
            throw new Exception("Intentionally throwing exception");
        } else {
            log.info("Message successfully consumed : {} ", message);
        }
    }

}
