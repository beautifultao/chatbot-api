package com.atao.chatbot.api.domain.zsxq.model.vo;
import lombok.Data;

import java.util.List;
@Data
public class UserSpecific
{
    private boolean liked;

    private List<String> liked_emojis;

    private boolean subscribed;
}