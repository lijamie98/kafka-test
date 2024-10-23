# How to run Kafka in SASL_SSL mode
Change directory to the `$PROJECT_DIR` then run:
```
docker-compose up
```
Kafka will be running modes on the following ports:

- `29092`: PLAINTEXT: 
- `29093`: SSL
- `29094`: SASL_SSL
- `29095`: SASL_PLAINTEXT

# Run the Kafka Test
The keystore and trust store files are located in hte `$PROJECT_DIR/secrets` folder. When running the tests, please make sure the working directory is the `$PROJECT_DIR`.

- `KafkaPlainTextTest`: Test Kafka in `PLAINTEXT` mode
- `KafkaSaslSslTest`: Test Kafka in `SASL_SSL` mode
- `KafkaSaslSslNoCertVerifyTest`: Test Kafka in `SSL` mode with no certificate verification
- `KafkaSaslPlainTextTest`: Test Kafka in `SASL_PLAINTEXT` mode

