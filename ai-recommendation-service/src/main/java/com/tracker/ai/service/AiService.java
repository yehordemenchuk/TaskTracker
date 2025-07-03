package com.tracker.ai.service;

import java.io.IOException;

public interface AiService {
    String getAiResponse(String prompt) throws IOException;
}
