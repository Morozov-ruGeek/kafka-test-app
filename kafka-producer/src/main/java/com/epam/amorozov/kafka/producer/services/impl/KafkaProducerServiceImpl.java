package com.epam.amorozov.kafka.producer.services.impl;

import com.epam.amorozov.kafka.producer.models.dtos.ShareDTO;
import com.epam.amorozov.kafka.producer.repositories.ShareRepository;
import com.epam.amorozov.kafka.producer.services.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<Long, ShareDTO> kafkaTemplate;
    private final ShareRepository shareRepository;

    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<Long, ShareDTO> kafkaTemplate, ShareRepository shareRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.shareRepository = shareRepository;
    }

    @Override
    public void singleSend(ShareDTO dto) {
        log.info("<= sending: {}", dto);
        kafkaTemplate.send("orders", dto);
    }

    @Override
    public void batchSend() {
        List<ShareDTO> shareDTOList = shareRepository.getAllShares();
        log.info("<= sending: {}", shareDTOList);
//        kafkaTemplate.send(topicName, shareDTOList);
//        todo create batchKafkaTemplate
    }
}
