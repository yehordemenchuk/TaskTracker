package com.tracker.analytics.dto;

import java.util.Map;

public record DataDto(Long habitId,
                      Map<String, String> userIdsData) {
}
