package com.tracker.ai.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic habitUpdationTopic() {
        return new NewTopic("habit-updation", 3, (short) 1);
    }

    @Bean
    public NewTopic habitDeletionTopic() {
        return new NewTopic("habit-deletion", 3, (short) 1);
    }

    @Bean
    public NewTopic userUpdationTopic() {
        return new NewTopic("user-updation", 3, (short) 1);
    }

    @Bean
    public NewTopic recommendationTopic() {
        return new NewTopic("recommendation", 3, (short) 1);
    }
}
