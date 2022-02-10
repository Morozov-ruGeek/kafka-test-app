package com.epam.amorozov.kafka.listener.services;

import com.epam.amorozov.kafka.listener.models.dtos.ShareDTO;

import java.util.List;

public interface KafkaConsumerService {
    void singleConsume(ShareDTO dto);
    void batchConsume(List<ShareDTO> dtos);
}
