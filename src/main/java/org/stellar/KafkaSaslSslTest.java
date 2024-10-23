package org.stellar;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

public class KafkaSaslSslTest extends KafkaTest {
  @Override
  @SuppressWarnings("Duplicates")
  public Properties getKafkaProperties() {
    Properties props = new Properties();
    // Kafka broker address
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29094");

    // SSL settings
    props.put("ssl.keystore.location", "./secrets/kafka.keystore.jks");
    props.put("ssl.truststore.location", "./secrets/kafka.truststore.jks");
    props.put("ssl.keystore.password", "test123");
    props.put("ssl.key.password", "test123");
    props.put("ssl.truststore.password", "test123");

    // SASL settings
    props.put("sasl.mechanism", "PLAIN");

    // SASL/SSL Configurations
    props.put("security.protocol", "SASL_SSL");
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
    KafkaSaslSslTest kafkaTest = new KafkaSaslSslTest();
    kafkaTest.run();
  }
}
