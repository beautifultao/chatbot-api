package com.atao.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

@Data
public class ShowComments
{
    private String comment_id;

    private String create_time;

    private Owner owner;

    private String text;

    private int likes_count;

    private int rewards_count;

    private boolean sticky;
}