package org.stellar;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

public class KafkaSaslPlainTextTest extends KafkaTest {
  @Override
  @SuppressWarnings("Duplicates")
  public Properties getKafkaProperties() {
    Properties props = new Properties();
    // Kafka broker address
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29095");

    // SASL settings
    props.put("sasl.mechanism", "PLAIN");

    // SASL Configurations
    props.put("security.protocol", "SASL_PLAINTEXT");
    props.put(
        "sasl.jaas.config",
        "org.apache.kafka.common.security.plain.PlainLoginModule required username='admin' password='admin-secret';");

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
    KafkaSaslPlainTextTest kafkaTest = new KafkaSaslPlainTextTest();
    kafkaTest.run();
  }
}
