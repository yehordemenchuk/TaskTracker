package com.tracker.notification.listener;

import com.tracker.notification.dto.RecommendationDto;
import com.tracker.notification.service.PushNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationListener {
    private PushNotificationService pushNotificationService;

    @KafkaListener(topics = "recommendation",
            groupId = "recommendation-group",
            containerFactory = "kafkaListenerContainerFactoryHabitSentEvent")
    public void recommendationListener(RecommendationDto recommendationDto) throws Exception {
        List<String> deviceTokens = recommendationDto.userDto().deviceTokens();

        for (String deviceToken : deviceTokens) {
            pushNotificationService.sendNotification(deviceToken, "Recommendation for you",
                    recommendationDto.recommendation());
        }
    }
}
