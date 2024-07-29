package com.atao.chatbot.api.domain.ai.model.aggregates;

import com.atao.chatbot.api.domain.ai.model.vo.Choices;
import com.atao.chatbot.api.domain.ai.model.vo.Usage;
import lombok.Data;

import java.util.List;

@Data
public class AIAnswer {
    private int code;

    private String message;

    private String sid;

    private List<Choices> choices;

    private Usage usage;
}
