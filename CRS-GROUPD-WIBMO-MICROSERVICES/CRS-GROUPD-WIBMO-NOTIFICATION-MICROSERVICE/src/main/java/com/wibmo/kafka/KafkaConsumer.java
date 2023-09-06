//package com.wibmo.kafka;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import com.wibmo.dto.NotificationDto;
//
//import org.springframework.kafka.annotation.EnableKafka;
//
//
//@EnableKafka
//@Configuration
//public class KafkaConsumer {
//
//	@Bean
//	public Map<String,Object> consumerConfigs(){
//		Map<String, Object> configProps = new HashMap<>();
//		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
//		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
//		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.wibmo.*");
//		configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
//		return configProps;
//	}
//	
//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory() {
////	var deserializer = new JsonDeserializer<NotificationDto>();
////	deserializer.addTrustedPackages("com.wibmo.*");
////	Map<String, Object> configProps = new HashMap<>();
////	configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////	configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-id");
////	configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////	configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
////	configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
////	configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.wibmo.*");
////	configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
////	return new DefaultKafkaConsumerFactory<>(configProps);
//		
//		return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),
//				new JsonDeserializer<>(Object.class,false));
//	}
//
//	@Bean
//	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
//	ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//	factory.setConsumerFactory(consumerFactory());
//	return factory;
//	}
//}

// Java Program Illustrating Kafka Configurations

package com.wibmo.kafka;

// Importing required classes
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
import com.wibmo.entity.Notification;

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

