package com.stratio.architecture.microservices.kafkaweatherservice.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
public class KafkaConfiguration {

  private final AppProperties properties;

  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    // list of host:port pairs used for establishing the initial connections
    // to the Kakfa cluster
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getKafka().getBootstrapServers());
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    // value to block, after which it will throw a TimeoutException
    props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 10000);
    // allows a pool of processes to divide the work of consuming and processing records
    props.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getKafka().getConsumerGroup());

    return props;
  }

  @SuppressWarnings("rawtypes")
  @Bean
  public ProducerFactory producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public KafkaTemplate kafkaTemplate() {
    return new KafkaTemplate(producerFactory());
  }

  public KafkaConfiguration(AppProperties properties) {
    this.properties = properties;
  }
}
