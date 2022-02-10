package com.epam.amorozov.kafka.producer.configs;

import com.epam.amorozov.kafka.producer.models.dtos.ShareDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.producer.client-id}")
    private String kafkaProducerId;

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        return props;
    }

    @Bean
    public StringJsonMessageConverter converter(){
        return new StringJsonMessageConverter();
    }

    @Bean
    public ProducerFactory<Long, ShareDTO> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<Long, ShareDTO> kafkaTemplate(){
        KafkaTemplate<Long, ShareDTO> template = new KafkaTemplate<>(producerFactory());
        template.setMessageConverter(converter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, List<ShareDTO>> batchProducerFactory(){
        Map<String, Object> batchConfig = producerConfig();
        batchConfig.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        batchConfig.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        return new DefaultKafkaProducerFactory<>(batchConfig);
    }

    @Bean
    public KafkaTemplate<Long, List<ShareDTO>> batchKafkaTemplate(){
        return new KafkaTemplate<>(batchProducerFactory());
    }
}
