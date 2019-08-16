package cn.videon.message.service;

import cn.videon.message.config.ApplicationProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;

@Service
public class KafkaService {

    private Logger log = LoggerFactory.getLogger(KafkaService.class);

    private final KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ApplicationProperties properties;

    /**
     * 注入KafkaTemplate
     *
     * @param kafkaTemplate kafka模版类
     */
    @Autowired
    public KafkaService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "#{groupId}", groupId = "#{groupId}")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        log.debug("kafka processMessage start");
        log.debug("processMessage, topic = {}, msg = {}", record.topic(), record.value());
        if (record.topic().equals(properties.getGroupId())) {
            log.debug("redis is locale");
            return;
        }
        redisTemplate.convertAndSend(record.topic(), record.value());
        log.info("kafka processMessage end");
    }

    public void sendMessage(String data) {
        log.debug("kafka sendMessage start");
        Arrays.stream(properties.getTopicName().split(","))
                .forEach(topic -> {
                    ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
                    future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
                        }

                        @Override
                        public void onSuccess(SendResult<Integer, String> result) {
                            log.info("kafka sendMessage success topic = {}, data = {}", topic, data);
                        }
                    });
                });
        log.info("kafka sendMessage end");
    }
}