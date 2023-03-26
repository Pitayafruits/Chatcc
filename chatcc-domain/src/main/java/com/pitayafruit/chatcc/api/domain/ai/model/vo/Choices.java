package com.pitayafruit.chatcc.api.domain.ai.model.vo;

import lombok.Data;

/**
 * 选择
 */
@Data
public class Choices {

    private String text;

    private int index;

    private String logprobs;

    private String finish_reason;
}
