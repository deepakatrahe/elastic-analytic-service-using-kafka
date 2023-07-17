package com.bng.elastic.controller;

import com.bng.elastic.response.GenericResponse;
import com.bng.elastic.service.ElasticPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elastic")
public class ElasticController {

    private final ElasticPushService elasticPushService;

    private final static Logger logger = LogManager.getLogger(ElasticController.class);

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @PostMapping(value = "/publish")
    public GenericResponse sendMessageToKafkaTopic(@RequestBody String request, @RequestHeader String guiTopic) {
        GenericResponse response = new GenericResponse();
        try {
            logger.info("Elastic Request - " + request);
            response = elasticPushService.sendData(request, guiTopic);
            logger.info("Elastic Response - " + response);
        } catch (Exception ex) {
            System.out.println("Exception in ElasticController - " + ex);
            logger.info("Exception in ElasticController - " + ex);
        }
        return response;

    }
}
