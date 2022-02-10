package com.epam.amorozov.kafka.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaListenerApplication.class, args);
    }

}
