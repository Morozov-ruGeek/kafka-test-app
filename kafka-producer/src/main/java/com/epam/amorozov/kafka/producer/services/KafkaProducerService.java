package com.epam.amorozov.kafka.producer.services;

import com.epam.amorozov.kafka.producer.models.dtos.ShareDTO;

public interface KafkaProducerService {
    void singleSend(ShareDTO dto);
    void batchSend();
}
