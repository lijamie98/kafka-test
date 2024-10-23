package org.stellar;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public abstract class KafkaTest {
  public abstract Properties getKafkaProperties();

  public KafkaProducer<String, String> createProducer() {
    Properties props = getKafkaProperties();
    return new KafkaProducer<>(props);
  }

  public void createKafkaTopic(String topicName, int numPartitions, short replicationFactor) {
    Properties props = getKafkaProperties();

    // Create Kafka Admin Client
    try (AdminClient adminClient = AdminClient.create(props)) {
      NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);

      // Create the topic
      adminClient.createTopics(Arrays.asList(newTopic)).all().get();
      System.out.println("Topic " + topicName + " created successfully");
    } catch (ExecutionException e) {
      System.out.println("Topic " + topicName + " already exists");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed to create topic: " + e.getMessage());
    }
  }

  public void sendMessages(KafkaProducer<String, String> producer, String topic) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key", "value");
    producer.send(
        record,
        (metadata, exception) -> {
          if (exception == null) {
            System.out.println(
                "Sent message to "
                    + metadata.topic()
                    + " partition "
                    + metadata.partition()
                    + " offset "
                    + metadata.offset());
          } else {
            System.out.println("Error sending message: " + exception.getMessage());
          }
        });
    producer.close();
  }

  public KafkaConsumer<String, String> createConsumer() {
    Properties props = getKafkaProperties();
    return new KafkaConsumer<>(props);
  }

  public void consumeMessages(KafkaConsumer<String, String> consumer, String topic) {
    consumer.subscribe(Arrays.asList(topic));
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
    records.forEach(
        record -> {
          System.out.println(
              "Received message: (key: "
                  + record.key()
                  + ", value: "
                  + record.value()
                  + ", partition: "
                  + record.partition()
                  + ", offset: "
                  + record.offset()
                  + ")");
        });
    consumer.close();
  }

  public void run() {
    String topic = "your-topic-name-1";

    createKafkaTopic(topic, 1, (short) 1);
    KafkaProducer<String, String> producer = createProducer();
    sendMessages(producer, topic);

    KafkaConsumer<String, String> consumer = createConsumer();
    consumeMessages(consumer, topic);
  }
}
