package cn.videon.message.config;

import cn.videon.message.service.RedisMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Configures Spring Data Redis support.
 */
@Configuration
public class MessagingConfiguration {

    @Autowired
    private ApplicationProperties properties;

    /**
     * @description: 定义topic在 application-dev.yml 中的 channelTopic 属性 多个topic 用 , 分割
     * @author: Limy
     * @date: 18/3/23
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        List<ChannelTopic> channelTopics = new ArrayList<>();
        for (String topic : properties.getChannel().split(",")) {
            channelTopics.add(new ChannelTopic(topic));
        }
        container.addMessageListener(listenerAdapter, channelTopics);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "onMessage");
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }


}
