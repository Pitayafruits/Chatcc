package com.pitayafruit.chatcc.api.domain.zsxq.model.res;

import com.pitayafruit.chatcc.api.domain.zsxq.model.vo.Topics;
import lombok.Data;

import java.util.List;

/**
 * 结果数据
 */

@Data
public class RespData {

    private List<Topics> topics;
}
