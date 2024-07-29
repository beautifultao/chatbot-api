package com.atao.chatbot.api.domain.ai.model.vo;

import lombok.Data;

@Data
public class Message
{
    private String role;

    private String content;
}