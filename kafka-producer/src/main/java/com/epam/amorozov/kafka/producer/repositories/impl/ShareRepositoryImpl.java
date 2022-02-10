package com.epam.amorozov.kafka.producer.repositories.impl;

import com.epam.amorozov.kafka.producer.models.dtos.ShareDTO;
import com.epam.amorozov.kafka.producer.repositories.ShareRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ShareRepositoryImpl implements ShareRepository {

    private List<ShareDTO> shares;

    @PostConstruct
    private void init(){
        shares = new ArrayList<>(Arrays.asList(
                new ShareDTO(1L, "share_one", new BigDecimal("100.00")),
                new ShareDTO(2L, "share_two", new BigDecimal("90.00")),
                new ShareDTO(3L, "share_three", new BigDecimal("80.00")),
                new ShareDTO(4L, "share_four", new BigDecimal("110.00")),
                new ShareDTO(5L, "share_five", new BigDecimal("120.00")),
                new ShareDTO(6L, "share_six", new BigDecimal("105.00")),
                new ShareDTO(7L, "share_seven", new BigDecimal("60.00")),
                new ShareDTO(8L, "share_eight", new BigDecimal("50.50")),
                new ShareDTO(9L, "share_nine", new BigDecimal("70.30")),
                new ShareDTO(10L, "share_ten", new BigDecimal("30.30")),
                new ShareDTO(11L, "share_eleven", new BigDecimal("20.50"))
        ));
    }

    @Override
    public List<ShareDTO> getAllShares() {
        return Collections.unmodifiableList(shares);
    }
}
