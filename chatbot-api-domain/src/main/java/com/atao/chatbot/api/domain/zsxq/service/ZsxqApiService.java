package com.atao.chatbot.api.domain.zsxq.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.atao.chatbot.api.domain.zsxq.ZsxqApi;
import com.atao.chatbot.api.domain.zsxq.model.aggregate.UnAnswerTopicAggregate;
import com.atao.chatbot.api.domain.zsxq.model.req.AnswerReq;
import com.atao.chatbot.api.domain.zsxq.model.req.ReqData;
import com.atao.chatbot.api.domain.zsxq.model.res.AnswerRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ZsxqApiService implements ZsxqApi {
    @Override
    public UnAnswerTopicAggregate queryUnAnswerTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=all&count=2");

        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type", "application/json;charset=utf-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            log.info("拉取问题数据。groupId:{},jsonStr:{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnswerTopicAggregate.class);
        } else {
            throw new RuntimeException("ErrCode: "+response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/comments");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json;charset=utf-8");
        post.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0");
/*
        String paramJson = """
                {
                  "req_data": {
                    "text": "好啊！没问题啊！\\n",
                    "image_ids": [],
                    "mentioned_user_ids": []
                  }
                }""";*/

        AnswerReq answerReq = new AnswerReq(new ReqData(text));
        String paramJson = JSONObject.from(answerReq).toString();

        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            log.info("回答星球问题。groupId:{},topicId:{},jsonStr:{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("Error:" + response.getStatusLine().getStatusCode());
        }
    }
}
