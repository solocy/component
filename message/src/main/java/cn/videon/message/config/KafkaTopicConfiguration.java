package cn.videon.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfiguration {

    private final ApplicationProperties properties;

    public KafkaTopicConfiguration(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean(name = "topics")
    public String[] kafkaTopicName() {
        return properties.getTopicName().split(",");
    }

    @Bean(name = "groupId")
    public String topicGroupId() {
        return properties.getGroupId();
    }

}