package com.tracker.analytics.event;

import com.tracker.analytics.dto.DataDto;
import com.tracker.analytics.dto.HabitDto;

public record HabitSentEvent(HabitDto habit,
                             DataDto descriptions) {
}
