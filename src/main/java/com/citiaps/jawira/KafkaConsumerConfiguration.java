package com.citiaps.jawira;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;


@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
	@Value("${kafka.bootstrap-servers}")
	private String kafkaBootstrapServers;
	@Value("${kafka.topic}")
	private String kafkaTopic;
	@Value("${zookeeper.group-id}")
	private String zookeeperGroupId;
	
	@Primary
	@Bean
	public Properties consumerProperties() {
		Properties props=new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,zookeeperGroupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return(props);
	}
	
	@Bean
    public Consumer<Long,String> simpleKafkaConsumer(Properties props) {
    	Consumer<Long, String> consumer=new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(kafkaTopic));

    	return(consumer);
    }
}
