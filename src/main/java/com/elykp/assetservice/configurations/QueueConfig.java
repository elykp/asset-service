package com.elykp.assetservice.configurations;

import com.elykp.assetservice.assets.FileProcessConsumer;
import com.elykp.assetservice.assets.FileProcessProducer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
  @Value("${queue.name}")
  private String queueName;

  @Bean
  public Queue queue() {
    return new Queue(queueName);
  }

  @Bean
  public FileProcessProducer fileProcessProducer() {
    return new FileProcessProducer();
  }

  @Bean
  public FileProcessConsumer fileProcessConsumer() {
    return new FileProcessConsumer();
  }
}
