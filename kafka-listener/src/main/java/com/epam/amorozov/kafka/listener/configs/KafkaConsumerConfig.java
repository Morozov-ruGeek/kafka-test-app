package com.epam.amorozov.kafka.listener.configs;

import com.epam.amorozov.kafka.listener.models.dtos.ShareDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    @Bean
    public Map<String, Object> config(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    @Bean
    public ConsumerFactory<Long, ShareDTO> kafkaConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(config());
    }

    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public BatchMessagingMessageConverter batchConverter(RecordMessageConverter converter){
        return new BatchMessagingMessageConverter(converter);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, ShareDTO> singleKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Long, ShareDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        factory.setBatchListener(false);
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, ShareDTO> batchKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Long, ShareDTO> batchFactory = new ConcurrentKafkaListenerContainerFactory<>();
        batchFactory.setConsumerFactory(kafkaConsumerFactory());
        batchFactory.setBatchListener(true);
        batchFactory.setMessageConverter(batchConverter(converter()));
        return batchFactory;
    }
}
