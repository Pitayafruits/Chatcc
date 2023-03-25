package com.pitayafruit.chatcc.api.domain.zsxq;

import com.pitayafruit.chatcc.api.domain.zsxq.model.aggregates.UnAnswerdQuestionsAggregates;

import java.io.IOException;

/**
 * 知识星球Api接口
 */
public interface IZsxqApi {

    UnAnswerdQuestionsAggregates queryUnAnswerdQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;
}
