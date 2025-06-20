package com.tracker.ai.event;

import com.tracker.ai.dto.DescriptionsDto;
import com.tracker.ai.dto.HabitDto;

public record HabitSentEvent(HabitDto habit,
                             DescriptionsDto descriptions) {
}
