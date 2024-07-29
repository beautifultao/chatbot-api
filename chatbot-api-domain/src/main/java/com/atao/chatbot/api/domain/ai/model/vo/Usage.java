package com.atao.chatbot.api.domain.ai.model.vo;

import lombok.Data;

@Data
public class Usage
{
    private int prompt_tokens;

    private int completion_tokens;

    private int total_tokens;
}