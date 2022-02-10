package com.epam.amorozov.kafka.producer.repositories;

import com.epam.amorozov.kafka.producer.models.dtos.ShareDTO;

import java.util.List;

public interface ShareRepository {
    List<ShareDTO> getAllShares();
}
