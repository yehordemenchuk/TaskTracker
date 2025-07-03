package com.tracker.analytics.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorDetails(LocalDateTime timestamp,
                           String message,
                           String path,
                           String status,
                           Map<String,Object> details) {
}
