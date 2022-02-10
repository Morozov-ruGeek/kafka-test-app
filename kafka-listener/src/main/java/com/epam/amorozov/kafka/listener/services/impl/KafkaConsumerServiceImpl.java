package com.epam.amorozov.kafka.listener.services.impl;

import com.epam.amorozov.kafka.listener.models.dtos.ShareDTO;
import com.epam.amorozov.kafka.listener.services.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    @KafkaListener(
            topics = "${share.topics.orders.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "singleKafkaListenerContainerFactory")
    public void singleConsume(ShareDTO dto) {
        log.info("=> consumed single message {}", dto);
    }

    @Override
    @KafkaListener(
            topics = "${share.topics.batch.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "batchKafkaListenerContainerFactory")
    public void batchConsume(List<ShareDTO> dtos) {
        log.info("=> consumed batch mesages {}", dtos);
    }
}
