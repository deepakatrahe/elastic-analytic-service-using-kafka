package com.bng.elastic.service;


import com.bng.elastic.response.GenericResponse;
import com.bng.elastic.util.ErrorCodes;
import org.elasticsearch.ElasticsearchException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;

@Service
public class ElasticPushService {

    private final KafkaProducer kafkaProducer;

    private static final Logger logger = LogManager.getLogger(ElasticPushService.class);

    public ElasticPushService(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    public GenericResponse sendData(String request,String guiTopic) {
        logger.info("Elastic Push service sendData -  called");
        System.out.println("sendData -  called");
        System.out.println("Elastic Request - " + request);
        System.out.println("Gui Topic - " + guiTopic);

        if(request.startsWith("\"") && request.endsWith("\"")) {
            int len = request.length();
            request = request.substring(1, len-1);
            System.out.println("Correct Req - " + request);
        }
        logger.info("Elastic Request - " + request);
        GenericResponse response=new GenericResponse();
        System.out.println("Before Decoding - " );
        logger.info("Before Decoding - " );
        byte[] decodedBytes = Base64.getDecoder().decode(request);
        String decodedString = new String(decodedBytes);
        System.out.println("Decoded String - " + decodedString);
        logger.info("Decoded String - " + decodedString);
        try {
            kafkaProducer.sendMessage(guiTopic, decodedString);
            logger.info("Message sent to Kafka");
            response.setStatus("SUCCESS");
            response.setReason("Message sent to Kafka");
            response.setStatusCode(ErrorCodes.SUCCESS.getCode());
        }
        catch (ElasticsearchException e) {
            // Handle Elasticsearch exception
            System.out.println(e);
            logger.info(e);
            response.setStatus("Failed");
            response.setReason(e.toString());
            response.setStatusCode(ErrorCodes.FAIL.getCode());

        } catch (Exception e1) {
            // Handle other exceptions
            System.out.println(e1);
            logger.info(e1);
            response.setStatus("Failed");
            response.setReason(e1.toString());
            response.setStatusCode(ErrorCodes.FAIL.getCode());
        }
        return response;
    }
}