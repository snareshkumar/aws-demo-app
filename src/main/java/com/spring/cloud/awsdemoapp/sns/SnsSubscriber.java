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
import com.amazonaws.services.sns.model.SubscribeRequest;

@RestController
@RequestMapping("/sns")
public class SnsSubscriber {
    private static final Logger log = LoggerFactory.getLogger(SnsSubscriber.class);

    @Autowired
    private AmazonSNSClient snsClient;

    @Value("${topic.arn}")
    private String topicARN;

    @GetMapping("/subscribe/{subscriptiontype}/{subscriptionvalue}")
    public String subscribe(@PathVariable("subscriptiontype") String subscriptionType,
            @PathVariable("subscriptionvalue") String subscriptionValue) {
        String subscriptionRequestStatus;
        log.info("Subscriptiontype value is {} and subscritpionvalue is {}", subscriptionType, subscriptionValue);
        if (subscriptionType.equalsIgnoreCase("email") || subscriptionType.equalsIgnoreCase("sms")) {
            SubscribeRequest subscribeRequest = new SubscribeRequest(topicARN, subscriptionType, subscriptionValue);
            snsClient.subscribe(subscribeRequest);
            subscriptionRequestStatus = "Subscription requested successfully. Check your " + subscriptionType;

        } else {
            subscriptionRequestStatus = "Invalid subscription type is received";

        }
        return subscriptionRequestStatus;
    }

}
