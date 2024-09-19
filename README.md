# How to run Kafka in SASL_SSL mode
Change directory to the `$PROJECT_DIR` then run:
```
docker-compose up
```
# Run Kafka Test
The keystore and trust store files are located in hte `$PROJECT_DIR/secrets` folder. When the `KafkaTest` class is invoked, please make sure the working directory is the `$PROJECT_DIR`. 
