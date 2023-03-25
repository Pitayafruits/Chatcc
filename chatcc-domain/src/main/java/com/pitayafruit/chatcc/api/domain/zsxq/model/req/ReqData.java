package com.pitayafruit.chatcc.api.domain.zsxq.model.req;

import lombok.Data;

/**
 * 请求对象
 */
@Data
public class ReqData {

    private String text;
    private String[] image_ids = new String[]{};
    private boolean silenced;

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }
}
