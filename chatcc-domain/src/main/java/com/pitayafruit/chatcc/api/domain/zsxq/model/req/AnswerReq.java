package com.pitayafruit.chatcc.api.domain.zsxq.model.req;

import lombok.Data;

/**
 * 请求问答接口信息
 */
@Data
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }
}
