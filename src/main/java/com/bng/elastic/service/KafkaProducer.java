package com.bng.elastic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.enable}")
    private boolean kafkaEnable;

    private static final Logger logger = LogManager.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        logger.info("Kafka Producer - " + message);
        if (kafkaEnable)
            this.kafkaTemplate.send(topic, message);
        else {
            logger.info("Kafka is disabled");
        }

    }
}
