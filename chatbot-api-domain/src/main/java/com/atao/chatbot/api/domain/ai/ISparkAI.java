package com.atao.chatbot.api.domain.ai;

import java.io.IOException;

public interface ISparkAI {
    String useSpark(String question) throws IOException;
}
