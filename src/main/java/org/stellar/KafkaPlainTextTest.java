package org.stellar;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

public class KafkaPlainTextTest extends KafkaTest {
  @Override
  @SuppressWarnings("Duplicates")
  public Properties getKafkaProperties() {
    Properties props = new Properties();
    // Kafka broker address
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
    props.put("security.protocol", "PLAINTEXT");

    // Others
    props.put(
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    props.put(
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    props.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "your-consumer-group");

    return props;
  }

  public static void main(String[] args) {
    KafkaPlainTextTest kafkaTest = new KafkaPlainTextTest();
    kafkaTest.run();
  }
}
