package com.wibmo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.wibmo.dto.NotificationDto;

import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@Configuration
public class KafkaConsumer {

	@Bean
	public Map<String,Object> consumerConfigs(){
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.wibmo.*");
		configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
		return configProps;
	}
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
//	var deserializer = new JsonDeserializer<NotificationDto>();
//	deserializer.addTrustedPackages("com.wibmo.*");
//	Map<String, Object> configProps = new HashMap<>();
//	configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//	configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
//	configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//	configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
//	configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//	configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.wibmo.*");
//	configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
//	return new DefaultKafkaConsumerFactory<>(configProps);
		
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),
				new JsonDeserializer<>(Object.class,false));
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
	ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
	}
}
