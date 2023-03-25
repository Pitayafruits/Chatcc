package com.pitayafruit.chatcc.api.domain.zsxq.model.aggregates;

import com.pitayafruit.chatcc.api.domain.zsxq.model.res.RespData;
import lombok.Data;

/**
 * 未回答问题的聚合信息
 */
@Data
public class UnAnswerdQuestionsAggregates {

    private boolean succeeded;

    private RespData resp_data;
}
