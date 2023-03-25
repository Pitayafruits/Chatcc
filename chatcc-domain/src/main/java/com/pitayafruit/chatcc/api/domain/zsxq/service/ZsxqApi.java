package com.pitayafruit.chatcc.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pitayafruit.chatcc.api.domain.zsxq.IZsxqApi;
import com.pitayafruit.chatcc.api.domain.zsxq.model.aggregates.UnAnswerdQuestionsAggregates;
import com.pitayafruit.chatcc.api.domain.zsxq.model.req.AnswerReq;
import com.pitayafruit.chatcc.api.domain.zsxq.model.req.ReqData;
import com.pitayafruit.chatcc.api.domain.zsxq.model.res.AnswerRes;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnswerdQuestionsAggregates queryUnAnswerdQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type","application/json;charset=UTF-8");

        CloseableHttpResponse reponse = httpClient.execute(get);

        if(reponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(reponse.getEntity());
            logger.info("拉取星球问题数据: groupId:{} jsonStr:{}",groupId,jsonStr);
            return JSON.parseObject(jsonStr,UnAnswerdQuestionsAggregates.class);
        }else{
            throw  new RuntimeException("queryUnAnswerdQuestionsTopicId err code is " + reponse.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");

        post.addHeader("cookie",cookie);
        post.addHeader("Content-Type","application/json;charset=UTF-8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

//        String paramJson = "{\n" +
//                "  \"req_data\": {\n" +
//                "    \"text\": \"看书去！\\n\",\n" +
//                "    \"image_ids\": [],\n" +
//                "    \"silenced\": false\n" +
//                "  }\n" +
//                "}";

        AnswerReq answerReq = new AnswerReq(new ReqData(text,silenced));
        String paramJson = JSONObject.toJSONString(answerReq);


        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答星球问题结果。groupId:{} topicId:{} jsonStr:{}",groupId,topicId,jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        }else{
            throw new RuntimeException("answer err code is " + response.getStatusLine().getStatusCode());
        }
    }

}
