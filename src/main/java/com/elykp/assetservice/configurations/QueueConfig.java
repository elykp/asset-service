package com.elykp.assetservice.configurations;

import com.elykp.assetservice.assets.FileProcessConsumer;
import com.elykp.assetservice.assets.FileProcessProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class QueueConfig {
  @Value("${queue.name}")
  private String queueName;

  @Bean
  public Queue queue() {
    return new Queue(queueName);
  }

  @Bean
  public FileProcessProducer fileProcessProducer() {
    log.info("Queue " + queueName + " initialized");
    return new FileProcessProducer();
  }

  @Bean
  public FileProcessConsumer fileProcessConsumer() {
    return new FileProcessConsumer();
  }
}
