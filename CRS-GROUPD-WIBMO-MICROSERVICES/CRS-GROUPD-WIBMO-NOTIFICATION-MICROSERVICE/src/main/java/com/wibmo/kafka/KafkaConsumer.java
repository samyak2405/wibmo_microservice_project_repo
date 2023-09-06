package com.wibmo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.wibmo.dto.NotificationDto;

// Annotation
@Configuration

// Class
public class KafkaConsumer {

	// Annotation
	@Bean

	// Method
	public ProducerFactory<String, NotificationDto> producerFactory()
	{

		// Creating a Map
		Map<String, Object> config = new HashMap<>();

		// Adding Configuration

		// 127.0.0.1:9092 is the default port number for
		// kafka
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"127.0.0.1:9092");
		config.put(
			ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
			StringSerializer.class);
		config.put(
			ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
			JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	// Annotation
	@Bean

	// Method
	public KafkaTemplate kafkaTemplate()
	{
		return new KafkaTemplate<>(producerFactory());
	}
	
		@Bean
		public ConsumerFactory<String, NotificationDto> consumerFactory()
		{

			// Creating a map of string-object type
			Map<String, Object> config = new HashMap<>();

			// Adding the Configuration
			config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
			config.put(ConsumerConfig.GROUP_ID_CONFIG,"students");
			config.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
			config.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				JsonDeserializer.class);

			// Returning message in JSON format
			return new DefaultKafkaConsumerFactory<>(
				config, new StringDeserializer(),
				new JsonDeserializer<>(NotificationDto.class));
		}

		// Creating a Listener
		@Bean
		public ConcurrentKafkaListenerContainerFactory<String,NotificationDto>
		notificationListener()
		{
			ConcurrentKafkaListenerContainerFactory<
				String, NotificationDto> factory
				= new ConcurrentKafkaListenerContainerFactory<>();
			factory.setConsumerFactory(consumerFactory());
														
			return factory;
		}

}

