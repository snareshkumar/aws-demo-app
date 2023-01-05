package com.spring.cloud.awsdemoapp.sqs;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Create REST end point to publish message into SQS queue
 * 
 */
@RestController
@RequestMapping("/sqs/publish")
public class SqsPublisher {

    org.slf4j.Logger log = LoggerFactory.getLogger(SqsPublisher.class);

    @Autowired
    private QueueMessagingTemplate sqsTemplate;

    @Value("${cloud.aws.end_point}")
    private String sqsEndPoint;

    @GetMapping("/{message}")
    public String publishMessage(@PathVariable String message) throws MessagingException {
        sqsTemplate.convertAndSend(sqsEndPoint, message);
        return "published successfully";
    }

}
