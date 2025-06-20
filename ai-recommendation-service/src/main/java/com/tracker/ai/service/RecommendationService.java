package com.tracker.ai.service;

import com.tracker.ai.dto.HabitDto;
import com.tracker.ai.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationService {
    private final AiService aiService;
    private final HabitService habitService;
    private final UserService userService;
    private static final String FULL_RECOMMENDATION_PROMPT = "Give a recommendations for this person: %s with this habits: %s";
    private static final String SHORT_DAILY_RECOMMENDATION_PROMPT = "Giver short daily recomendation for a push notification with emojis for person: %s with this habits: %s";

    private String buildPromptForAi(String promptBase, long userId) {
        UserDto userDto = userService.getById(userId);

        List<HabitDto> userHabits = habitService.getAllHabitsOfUser(userId);

        StringBuilder sb = new StringBuilder();

        userHabits.forEach(habitDto -> sb.append(habitDto.getName()).append("\n"));

        return String.format(promptBase, userDto, sb);
    }

    public String getFullRecommendation(long userId) throws IOException {
        return aiService.getAiResponse(buildPromptForAi(FULL_RECOMMENDATION_PROMPT, userId));
    }

    public String getShortRecommendation(long userId) throws IOException {
        return aiService.getAiResponse(buildPromptForAi(SHORT_DAILY_RECOMMENDATION_PROMPT, userId));
    }
}
