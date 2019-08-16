package cn.videon.message.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(RedisMessageSubscriber.class);

    @Autowired
    private KafkaService kafkaService;

    /**
     * @description: 接收redis中的消息
     * @param:
     * @return:
     * @author: Limy
     * @date: 18/3/23
     */
    @Override
    public void onMessage(final Message message, final byte[] bytes) {
        log.info("redis is get message -> {},bytes -> {}", message, new String(bytes));
        kafkaService.sendMessage(new String(message.getBody()));
        log.info("kafka send redis message success !");
    }
}
