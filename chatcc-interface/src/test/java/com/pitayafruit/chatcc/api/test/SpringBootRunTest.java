package com.pitayafruit.chatcc.api.test;

import com.alibaba.fastjson.JSON;
import com.pitayafruit.chatcc.api.domain.ai.IOpenAI;
import com.pitayafruit.chatcc.api.domain.zsxq.IZsxqApi;
import com.pitayafruit.chatcc.api.domain.zsxq.model.aggregates.UnAnswerdQuestionsAggregates;
import com.pitayafruit.chatcc.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatcc.groupId}")
    private String groupId;

    @Value("${chatcc.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnswerdQuestionsAggregates unAnswerdQuestionsAggregates = zsxqApi.queryUnAnswerdQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnswerdQuestionsAggregates));

        List<Topics> topics = unAnswerdQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId:{} text:{} ",topicId,text);
            //回答问题
            zsxqApi.answer(groupId,cookie,topicId,text,false);
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT("Java怎么开发后台管理系统");
        logger.info("测试结果：{}",response);
    }

}
