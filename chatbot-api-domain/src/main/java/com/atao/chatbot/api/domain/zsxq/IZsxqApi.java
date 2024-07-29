package com.atao.chatbot.api.domain.zsxq;

import com.atao.chatbot.api.domain.zsxq.model.aggregate.UnAnswerTopicAggregate;

import java.io.IOException;

public interface IZsxqApi {
    UnAnswerTopicAggregate queryUnAnswerTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text) throws IOException;
}
