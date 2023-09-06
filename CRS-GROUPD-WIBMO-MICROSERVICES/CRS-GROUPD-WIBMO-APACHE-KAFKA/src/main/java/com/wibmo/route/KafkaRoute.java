package com.wibmo.route;



import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
      
		String topicName = "topic=TestTopic";
		String kafkaServer = "kafka:localhost:9092";
		//String broker="broker=brokers";
		String zooKeeperHost = "zookeeperHost=localhost&zookeeperPort=2181";
		String serializerClass = "serializerClass=kafka.serializer.StringEncoder";

		String toKafka = new StringBuilder().append(kafkaServer).append("?").append(topicName).append("&")
				.append(zooKeeperHost).append("&").append(serializerClass).toString();
	/*	//String toKafka="kafka://localhost:9092?topic=TestTopic&brokers=localhost:9092";*/
// before 2.13
		
		from("file:D://inboxKafka?noop=true").split().tokenize("\n").to("kafka:TestTopic?brokers=localhost:9092");
	}
}