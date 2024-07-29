package com.atao.chatbot.api.domain.ai.model.vo;

import lombok.Data;

@Data
public class Choices
{
    private Message message;

    private int index;
}