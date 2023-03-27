package com.pitayafruit.chatcc.api.application.job;

import com.alibaba.fastjson.JSON;
import com.pitayafruit.chatcc.api.domain.ai.IOpenAI;
import com.pitayafruit.chatcc.api.domain.zsxq.IZsxqApi;
import com.pitayafruit.chatcc.api.domain.zsxq.model.aggregates.UnAnswerdQuestionsAggregates;
import com.pitayafruit.chatcc.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatcc.groupId}")
    private String groupId;

    @Value("${chatcc.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void run(){
         try{
             //0.避免风控
             GregorianCalendar calendar = new GregorianCalendar();
             int hour = calendar.get(Calendar.HOUR_OF_DAY);
             if(hour > 22 || hour < 7){
                 logger.info("夜深了，chatGPT休息中.....");
                 return;
             }
             //1.拉取问题
             UnAnswerdQuestionsAggregates unAnswerdQuestionsAggregates = zsxqApi.queryUnAnswerdQuestionsTopicId(groupId, cookie);
             logger.info("测试结果：{}", JSON.toJSONString(unAnswerdQuestionsAggregates));
             List<Topics> topics = unAnswerdQuestionsAggregates.getResp_data().getTopics();
             //2.避免风控，问题一个个回答
             if(topics == null || topics.isEmpty()){
                 logger.info("没有待回答的问题！");
                 return;
             }
             Topics topic = topics.get(0);
             String answer = openAI.doChatGPT(topic.getQuestion().getText().trim());
             //3.回复问题
             boolean answerStatus = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
             logger.info("问题编号:{} 问题内容:{} 问题答案:{} 回答状态:{} ",topic.getTopic_id(),topic.getQuestion().getText(),answer,answerStatus);
         }catch (Exception e){
             logger.error("回答异常:",e);
         }
    }
}
