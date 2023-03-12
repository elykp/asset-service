package com.elykp.assetservice.assets;

import com.elykp.assetservice.assets.dto.FileProcessMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class FileProcessProducer {
  @Autowired
  private Queue queue;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendMsg(final FileProcessMessage fileProcessMessage) {
    rabbitTemplate.convertAndSend(queue.getName(), fileProcessMessage);
  }
}
