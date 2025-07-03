package com.tracker.analytics.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class HabitDto {
    private final long id;
    private final String name;
    private String description;
    private final String type;
    private byte targetPerWeek;
    private byte currentWeekProgress;

    @Override
    public String toString() {
        return "Habit name: " + name + "\n"
                + " description: " + description + "\n"
                + " type: " + type + "\n";
    }
}
