/**
 * 
 */
package com.wibmo.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * 
 */
public class KafkaRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		String topicName = "topic=TestTopic";
		String kafkaServer = "kafka:localhost:9092";
		//String broker="broker=brokers";
		String zooKeeperHost = "zookeeperHost=localhost&zookeeperPort=2181";
		String serializerClass = "serializerClass=kafka.serializer.StringEncoder";

		String toKafka = new StringBuilder().append(kafkaServer).append("?").append(topicName).append("&")
				.append(zooKeeperHost).append("&").append(serializerClass).toString();
	
		
	}

	
	
}
