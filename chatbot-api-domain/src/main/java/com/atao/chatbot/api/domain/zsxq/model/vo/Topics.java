package com.atao.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class Topics
{
    private String topic_id;

    private Group group;

    private String type;

    private Talk talk;

    private List<ShowComments> show_comments;

    private int likes_count;

    private int rewards_count;

    private int comments_count;

    private int reading_count;

    private int readers_count;

    private boolean digested;

    private boolean sticky;

    private String create_time;

    private UserSpecific user_specific;
}