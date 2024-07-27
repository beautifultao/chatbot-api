package com.atao.chatbot.api.domain.zsxq.model.aggregate;

import com.atao.chatbot.api.domain.zsxq.model.res.ResData;
import lombok.Data;

/**
 * 为回答问题的聚合信息
 */
@Data
public class UnAnswerTopicAggregate {
    private boolean succeeded;
    private ResData resp_data;
}
