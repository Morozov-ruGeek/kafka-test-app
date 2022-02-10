package com.epam.amorozov.kafka.producer.controllers;

import com.epam.amorozov.kafka.producer.models.dtos.ShareDTO;
import com.epam.amorozov.kafka.producer.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public void send(@RequestBody ShareDTO dto){
        kafkaProducerService.singleSend(dto);
    }
}
