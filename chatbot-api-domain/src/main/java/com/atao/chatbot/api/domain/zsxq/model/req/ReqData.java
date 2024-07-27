package com.atao.chatbot.api.domain.zsxq.model.req;

import lombok.Data;

/**
 * 请求数据格式
 */
@Data
public class ReqData {
    private String text;

    private String[] image_ids = new String[]{};

    private String[] mentioned_user_ids = new String[]{};

    public ReqData(String text) {
        this.text = text;
    }
}
