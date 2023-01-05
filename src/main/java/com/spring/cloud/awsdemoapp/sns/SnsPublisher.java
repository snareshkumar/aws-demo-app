package com.spring.cloud.awsdemoapp.sns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

/*
 * This controller created for AWS SNS demo.
 * 
 * Exposed two REST endpoint to test the publish 
 * operation.
 * 
 */
@RestController
@RequestMapping("/sns")
public class SnsPublisher {

    private Logger log = LoggerFactory.getLogger(SnsPublisher.class);

    @Autowired
    private AmazonSNSClient snsClient;

    @Value("${topic.arn}")
    private String topicARN;

    @GetMapping("/publish/{message}")
    public void publishMessage(@PathVariable String message) {
        PublishRequest request = new PublishRequest(topicARN, message, "AWS SNS Message");
        snsClient.publish(request);
        log.info("Message published successfully to the SNS topic");
    }

}
