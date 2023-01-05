package com.spring.cloud.awsdemoapp.sqs;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

/**
 * Class created for consuming messages from AWS SQS queue.
 * 
 * Also it deals with deletion message policy(if there is any exception happened
 * SQS message will be retried)
 */
@Component
public class SqsConsumer {

    org.slf4j.Logger log = LoggerFactory.getLogger(SqsPublisher.class);

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
