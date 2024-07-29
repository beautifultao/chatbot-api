package com.atao.chatbot.api.domain.ai.service;

import com.alibaba.fastjson2.JSON;
import com.atao.chatbot.api.domain.ai.ISparkAI;
import com.atao.chatbot.api.domain.ai.model.aggregates.AIAnswer;
import com.atao.chatbot.api.domain.ai.model.vo.Choices;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class SparkAIService implements ISparkAI {
    @Value("${SparkAI.key}")
    private String sparkKey;

    @Value("${SparkAI.secret}")
    private String sparkSecret;

    @Override
    public String useSpark(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://spark-api-open.xf-yun.com/v1/chat/completions");
        post.addHeader("Authorization", String.format("Bearer %s:%s", sparkKey, sparkSecret)); // 使用格式化字符串
        post.addHeader("Content-Type", "application/json");

        String paramJson = String.format("""
                {
                     "model": "generalv3.5",
                     "messages": [
                         {
                             "role": "user",
                             "content": "%s"
                         }
                     ]
                }""", question);

        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(res, AIAnswer.class);

            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getMessage().getContent());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("Error"+ response.getStatusLine().getStatusCode());
        }
    }
}
